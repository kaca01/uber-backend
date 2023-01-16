package com.example.test.service;

import com.example.test.domain.communication.Message;
import com.example.test.domain.communication.Note;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.User;
import com.example.test.dto.AllDTO;
import com.example.test.dto.communication.MessageDTO;
import com.example.test.dto.communication.NoteDTO;
import com.example.test.dto.ride.RideDTO;
import com.example.test.dto.user.ChangePasswordDTO;
import com.example.test.dto.user.ResetPasswordDTO;
import com.example.test.dto.user.UserDTO;
import com.example.test.enumeration.MessageType;
import com.example.test.repository.communication.IMessageRepository;
import com.example.test.repository.communication.INoteRepository;
import com.example.test.repository.ride.IRideRepository;
import com.example.test.repository.user.IUserRepository;
import com.example.test.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

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
    public BCryptPasswordEncoder passwordEncoder;

    @Override
    public User changePassword(Long id, ChangePasswordDTO changePasswordDTO) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null) return null;

        if(!passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword())) return null;

        user.setLastPasswordResetDate(new Timestamp(new Date().getTime()));
        user.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
        userRepository.save(user);
        return user;
    }

    @Override
    public User sendResetEmail(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null) return null;

        user.setLastPasswordResetDate(new Timestamp(new Date().getTime()));
        userRepository.save(user);
        return user;
    }

    @Override
    public User resetEmail(Long id, ResetPasswordDTO resetPasswordDTO) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null) return null;

        user.setLastPasswordResetDate(new Timestamp(new Date().getTime()));
        userRepository.save(user);
        return user;
    }

    @Override
    @Transactional
    public List<RideDTO> getRides(Long id, int page, int size, String sort, String from, String to) {
        // todo page, size
        User user = userRepository.findById(id).orElse(null);
        if(user == null) return null;
        List<Ride> rides = rideRepository.findByPassengers_id(id);
        if (rides.isEmpty()) {
            rides = rideRepository.findRidesByDriverId(id);
            System.out.println(rides);
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
    public List<UserDTO> get(int page, int size) {
        List<UserDTO> userDTOS = new ArrayList<>();
        // list of users is never empty or null
        for(User user : userRepository.findAll()) {
            userDTOS.add(new UserDTO(user));
        }
        return userDTOS;
    }

    @Override
    public List<String> login(String email, String password) {
        return null;
    }

    @Override
    @Transactional
    public List<MessageDTO> getMessages(Long id) {
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
    public MessageDTO insertMessage(Long senderId, MessageDTO requestMessage) {
        User sender = userRepository.findById(senderId).orElse(null);
        Ride ride = rideRepository.findById(requestMessage.getRideId()).orElse(null);
        User receiver = userRepository.findById(requestMessage.getReceiverId()).orElse(null);
        if(sender == null || ride == null || receiver == null) return null;

        Message message = new Message();
        message.setTimeOfSending(new Date());
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setMessage(requestMessage.getMessage());
        message.setType(MessageType.valueOf(requestMessage.getType()));
        message.setRide(ride);
        return new MessageDTO(message);
    }

    @Override
    public Boolean block(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) return false;
        user.setBlocked(true);
        userRepository.save(user);
        return true;
    }

    @Override
    public Boolean unblock(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) return false;
        user.setBlocked(false);
        userRepository.save(user);
        return true;
    }

    @Override
    public NoteDTO insertNote(Long id, NoteDTO requestNote) throws ParseException {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) return null;
        Note note = new Note(requestNote);
        note.setUser(user);
        noteRepository.save(note);
        requestNote.setId(note.getId());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        requestNote.setDate(formatter.format(note.getDate()));
        return requestNote;
    }

    @Override
    public AllDTO<NoteDTO> getNotes(Long id, int page, int size) {
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

}
