package com.example.test.service;

import com.example.test.Mockup;
import com.example.test.domain.communication.Message;
import com.example.test.domain.communication.Note;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Passenger;
import com.example.test.domain.user.User;
import com.example.test.dto.AllDTO;
import com.example.test.dto.communication.MessageDTO;
import com.example.test.dto.communication.NoteDTO;
import com.example.test.dto.ride.RideDTO;
import com.example.test.dto.user.UserDTO;
import com.example.test.enumeration.MessageType;
import com.example.test.repository.communication.INoteRepository;
import com.example.test.repository.ride.IRideRepository;
import com.example.test.repository.user.IUserRepository;
import com.example.test.service.interfaces.IUserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserRepository iUserRepository;
    @Autowired
    INoteRepository iNoteRepository;
    @Autowired
    IRideRepository iRideRepository;


    @Override
    @Transactional
    public List<RideDTO> getRides(Long id, int page, int size, String sort, String from, String to) {
        // todo page, size
        User user = iUserRepository.findById(id).orElse(null);
        if (user == null) return null;
        List<Ride> rides = iRideRepository.findRidesByDriverId(id);
        if (rides == null) {
            rides = iRideRepository.findByPassengers_id(id);
            if (rides == null) return null;
        }
        List<RideDTO> rideDTOS = new ArrayList<>();
        for (Ride ride : rides) {
            rideDTOS.add(new RideDTO(ride));
        }
        return rideDTOS;
    }

    @Override
    // page:1, size:5
    public List<UserDTO> get(int page, int size) {
//        List<User> usersFromPage = new ArrayList<>();
//        int last = page*size;
//        for(int i = last-size; i<last; i++) {
//            try {
//                usersFromPage.add(users.get(i));
//            } catch (Exception e) {
//                break;
//            }
//        } return usersFromPage;
        return null;
    }

    @Override
    public List<String> login(String email, String password) {
//        List<String> tokens = new ArrayList<>();
//        for (User u : users) {
//            if(Objects.equals(u.getEmail(), email) && Objects.equals(u.getPassword(), password)) {
//                tokens.add("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c");
//                tokens.add("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c");
//                return tokens;
//            }
//        }
        return null;
    }

    @Override
    public List<MessageDTO> getMessages(Long id) {
//        List<Message> userMessage = new ArrayList<>();
//        for(Message m : messages) {
//            if(Objects.equals(m.getReceiver().getId(), id)) userMessage.add(m);
//            else if (Objects.equals(m.getSender().getId(), id)) userMessage.add(m);
//        }
//        if (!userMessage.isEmpty()) return userMessage;
        return null;
    }

    @Override
    public MessageDTO insertMessage(Long id, MessageDTO requestMessage) {
//        User u = findUserById(id);
//        if (u == null) return null;
//        Ride r = findRideById(requestMessage.getRideId());
//        if (r == null) return null;
//        Message message = new Message();
//        message.setId(messages.get(messages.size()-1).getId()+1);
//        message.setTimeOfSending(new Date());
//        message.setSender(u);
//        message.setReceiver(findUserById(requestMessage.getReceiverId()));
//        message.setMessage(requestMessage.getMessage());
//        message.setType(MessageType.valueOf(requestMessage.getType()));
//        message.setRide(r);
//        return message;
        return null;
    }

    @Override
    public Boolean block(Long id) {
        User user = iUserRepository.findById(id).orElse(null);
        if (user == null) return false;
        user.setBlocked(true);
        iUserRepository.save(user);
        return true;
    }

    @Override
    public Boolean unblock(Long id) {
        User user = iUserRepository.findById(id).orElse(null);
        if (user == null) return false;
        user.setBlocked(false);
        iUserRepository.save(user);
        return true;
    }

    @Override
    public NoteDTO insertNote(Long id, NoteDTO requestNote) throws ParseException {
        User user = iUserRepository.findById(id).orElse(null);
        if (user == null) return null;
        Note note = new Note(requestNote);
        note.setUser(user);
        iNoteRepository.save(note);
        requestNote.setId(note.getId());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        requestNote.setDate(formatter.format(note.getDate()));
        return requestNote;
    }

    @Override
    public AllDTO<NoteDTO> getNotes(Long id, int page, int size) {
        List<Note> userNotes = iNoteRepository.findByUserId(id);
        List<NoteDTO> userNoteDTOs = new ArrayList<>();
        for (Note note : userNotes) userNoteDTOs.add(new NoteDTO(note));
        AllDTO<NoteDTO> allDTO = new AllDTO<>(userNoteDTOs.size(), userNoteDTOs);
        return allDTO;
    }
}
