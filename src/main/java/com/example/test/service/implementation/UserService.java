package com.example.test.service.implementation;

import com.example.test.domain.account.ImageData;
import com.example.test.domain.communication.Message;
import com.example.test.domain.communication.Note;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.ResetPassword;
import com.example.test.domain.user.User;
import com.example.test.dto.AllDTO;
import com.example.test.dto.communication.MessageDTO;
import com.example.test.dto.communication.NoteDTO;
import com.example.test.dto.ride.RideDTO;
import com.example.test.dto.user.*;
import com.example.test.enumeration.MessageType;
import com.example.test.exception.BadRequestException;
import com.example.test.exception.NotFoundException;
import com.example.test.repository.account.IImageDataRepository;
import com.example.test.repository.communication.IMessageRepository;
import com.example.test.repository.communication.INoteRepository;
import com.example.test.repository.ride.IRideRepository;
import com.example.test.repository.user.IResetPasswordRepository;
import com.example.test.repository.user.IUserRepository;
import com.example.test.security.TokenUtils;
import com.example.test.service.interfaces.IUserService;
import com.example.test.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    IUserRepository userRepository;
    @Autowired
    INoteRepository noteRepository;
    @Autowired
    IRideRepository rideRepository;
    @Autowired
    IMessageRepository messageRepository;
    @Autowired
    IResetPasswordRepository resetPasswordRepository;
    @Autowired
    IImageDataRepository imageDataRepository;
//    @Autowired
//    TokenUtils tokenUtils;
//    @Autowired
//    AuthenticationManager authenticationManager;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public void changePassword(Long id, ChangePasswordDTO changePasswordDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User does not exist!"));

        if(!passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword()))
            throw new BadRequestException("Current password is not matching!");

        user.setLastPasswordResetDate(new Timestamp(new Date().getTime()));
        user.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    public void sendResetEmail(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User does not exist!"));        user.setLastPasswordResetDate(new Timestamp(new Date().getTime()));
        userRepository.save(user);
    }

    @Override
    public void resetEmail(Long id, ResetPasswordDTO resetPasswordDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User does not exist!"));

        ResetPassword resetPassword = resetPasswordRepository.findResetPasswordByUserId(id);
        Date expiredDate = resetPassword.getExpiredDate();

        if(!resetPasswordDTO.getCode().equals(resetPassword.getCode()) || expiredDate.before(new Date()))
            throw new BadRequestException("Code is expired or not correct!");

        resetPassword.setCode(resetPasswordDTO.getCode());
        resetPasswordRepository.save(resetPassword);

        user.setLastPasswordResetDate(new Timestamp(new Date().getTime()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public List<RideDTO> getRides(Long id) {
        userRepository.findById(id).orElseThrow(() -> new NotFoundException("User does not exist!"));

        List<Ride> rides = rideRepository.findByPassengers_id(id);
        if (rides.isEmpty()) {
            rides = rideRepository.findRidesByDriverId(id);
            if (rides.isEmpty()) return null;
        }
        // convert to DTO
        List<RideDTO> rideDTOS = new ArrayList<>();
        for (Ride ride : rides) {
            rideDTOS.add(new RideDTO(ride));
        }
        return rideDTOS;
    }

    @Override
    // page:1, size:5
    public List<UserDTO> get() {
        List<UserDTO> userDTOS = new ArrayList<>();
        // list of users is never empty or null
        for(User user : userRepository.findAll()) {
            userDTOS.add(new UserDTO(user));
        }
        return userDTOS;
    }

    @Override
    public void login(LoginDTO loginDTO) {


        // return a token in response to successful authentication
//        return new UserTokenState(access, refresh);
    }

    @Override
    @Transactional
    public List<MessageDTO> getMessages(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User does not exist!"));

        List<Message> messages = messageRepository.findMessageBySenderIdOrReceiverId(id, id);
        if(messages.isEmpty()) return null;
        // convert to DTO
        List<MessageDTO> messageDTOS = new ArrayList<>();
        for(Message message : messages) {
            messageDTOS.add(new MessageDTO(message));
        }
        return messageDTOS;
    }

    @Override
    @Transactional
    public List<MessageDTO> getSupportMessages(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User does not exist!"));

        List<Message> messages = messageRepository.findMessageBySenderIdOrReceiverId(id, id);
        if(messages.isEmpty()) return null;

        messages.removeIf(m -> !m.getType().equals(MessageType.SUPPORT));
        if(messages.isEmpty()) return null;

        // convert to DTO
        List<MessageDTO> messageDTOS = new ArrayList<>();
        for(Message message : messages) {
            messageDTOS.add(new MessageDTO(message));
        }
        return messageDTOS;
    }

    @Override
    public MessageDTO insertMessage(Long receiverId, MessageDTO requestMessage, User sender) {
        //User receiver = userRepository.findById(receiverId).orElseThrow(() -> new NotFoundException("Receiver does not exist!"));
        User receiver = userRepository.findById(receiverId).orElse(null);
        //Ride ride = rideRepository.findById(requestMessage.getRideId()).orElseThrow(() -> new NotFoundException("Ride does not exist!"));
        Ride ride = rideRepository.findById(requestMessage.getRideId()).orElse(null);
        sender = userRepository.findById(sender.getId()).orElseThrow(() -> new NotFoundException("User does not exist!"));

        Message message = new Message();
        message.setTimeOfSending(new Date());
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setMessage(requestMessage.getMessage());
        message.setType(MessageType.valueOf(requestMessage.getType()));
        message.setRide(ride);
        message = messageRepository.save(message);
        return new MessageDTO(message);
    }

    @Override
    public void block(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User does not exist!"));
        if(user.isBlocked())
            throw new BadRequestException("User already blocked!");
        user.setBlocked(true);
        userRepository.save(user);
    }

    @Override
    public void unblock(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User does not exist!"));
        if(!user.isBlocked())
            throw new BadRequestException("User is not blocked!");
        user.setBlocked(false);
        userRepository.save(user);
    }

    @Override
    public NoteDTO insertNote(Long id, NoteDTO requestNote) throws ParseException {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User does not exist!"));

        Note note = new Note(requestNote);
        note.setUser(user);
        noteRepository.save(note);
        requestNote.setId(note.getId());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        requestNote.setDate(formatter.format(note.getDate()));
        return requestNote;
    }

    @Override
    public AllDTO<NoteDTO> getNotes(Long id) {
        userRepository.findById(id).orElseThrow(() -> new NotFoundException("User does not exist!"));

        List<Note> userNotes = noteRepository.findByUserId(id);
        List<NoteDTO> userNoteDTOs = new ArrayList<>();
        for (Note note : userNotes) userNoteDTOs.add(new NoteDTO(note));
        return new AllDTO<>(userNoteDTOs.size(), userNoteDTOs);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email '%s' is not found!", email)));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public void uploadImage(Long id, MultipartFile file) throws IOException {
        ImageData imageData = imageDataRepository.findByUserId(id);
        if(imageData != null) imageDataRepository.delete(imageData);

        imageDataRepository.save(ImageData.builder()
                .userId(id)
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
    }

    public byte[] downloadImage(Long id) {
        ImageData image = imageDataRepository.findByUserId(id);
        if(image == null)
            throw new NotFoundException("User hasn't image!");

        return ImageUtils.decompressImage(image.getImageData());
    }

    public void deleteImage(Long id) {
        ImageData imageData = imageDataRepository.findByUserId(id);
        if(imageData != null) imageDataRepository.delete(imageData);
    }
}
