package com.example.test.service;

import com.example.test.Mockup;
import com.example.test.domain.communication.Message;
import com.example.test.domain.communication.Note;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Passenger;
import com.example.test.domain.user.User;
import com.example.test.dto.communication.MessageDTO;
import com.example.test.enumeration.MessageType;
import com.example.test.repository.user.IUserRepository;
import com.example.test.service.interfaces.IUserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserRepository iUserRepository;

    Mockup mockup = new Mockup();
    ArrayList<User> users = mockup.users;
    ArrayList<Note> notes = mockup.notes;
    ArrayList<Ride> rides = mockup.rides;
    ArrayList<Message> messages = mockup.messages;

    @SneakyThrows
    @Override
    public List<Ride> getRides(Long id, int page, int size, String sort, String from, String to) {
        // todo page, size
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date fromDate = formatter.parse(from);;
        Date toDate = formatter.parse(to);

        List<Ride> userRides = new ArrayList<>();
        for (Ride r : rides) {
            // check for driver
            if(Objects.equals(r.getDriver().getId(), id) && r.getStartTime().after(fromDate)
                    && r.getStartTime().before(toDate))
                userRides.add(r);
            // checking for passenger
            for(Passenger p : r.getPassengers() ) {
                if(Objects.equals(p.getId(), id) && r.getStartTime().after(fromDate)
                && r.getStartTime().before(toDate)) {
                    userRides.add(r);
                }
            }
        }
        if(!userRides.isEmpty()) {
            userRides.sort(Comparator.comparing(Ride::getStartTime));
            return userRides;
        }
        return null;
    }

    @Override
    // page:1, size:5
    public List<User> get(int page, int size) {
        List<User> usersFromPage = new ArrayList<>();
        int last = page*size;
        for(int i = last-size; i<last; i++) {
            try {
                usersFromPage.add(users.get(i));
            } catch (Exception e) {
                break;
            }
        } return usersFromPage;
    }

    @Override
    public List<String> login(String email, String password) {
        List<String> tokens = new ArrayList<>();
        for (User u : users) {
            if(Objects.equals(u.getEmail(), email) && Objects.equals(u.getPassword(), password)) {
                tokens.add("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c");
                tokens.add("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c");
                return tokens;
            }
        }
        return null;
    }

    @Override
    public List<Message> getMessages(Long id) {
        List<Message> userMessage = new ArrayList<>();
        for(Message m : messages) {
            if(Objects.equals(m.getReceiver().getId(), id)) userMessage.add(m);
            else if (Objects.equals(m.getSender().getId(), id)) userMessage.add(m);
        }
        if (!userMessage.isEmpty()) return userMessage;
        return null;
    }

    @Override
    public Message insertMessage(Long id, MessageDTO requestMessage) {
        User u = findUserById(id);
        if (u == null) return null;
        Ride r = findRideById(requestMessage.getRideId());
        if (r == null) return null;
        Message message = new Message();
        message.setId(messages.get(messages.size()-1).getId()+1);
        message.setTimeOfSending(new Date());
        message.setSender(u);
        message.setReceiver(findUserById(requestMessage.getReceiverId()));
        message.setMessage(requestMessage.getMessage());
        message.setType(MessageType.valueOf(requestMessage.getType()));
        message.setRide(r);
        return message;
    }

    @Override
    public Boolean block(Long id) {
        User user = iUserRepository.findById(id);
        if (user == null) return false;
        user.setBlocked(true);
        iUserRepository.save(user);
        return true;
    }

    @Override
    public Boolean unblock(Long id) {
        User user = iUserRepository.findById(id);
        if (user == null) return false;
        user.setBlocked(false);
        iUserRepository.save(user);
        return true;
    }

    @Override
    public Note insertNote(Long id, Note requestNote) {
        User u = findUserById(id);
        if (u == null) return null;
        requestNote.setId(notes.get(notes.size()-1).getId()+1);
        requestNote.setDate(new Date());
        requestNote.setUser(u);
        return requestNote;
    }

    @Override
    public List<Note> getNotes(Long id, int page, int size) {
        List<Note> userNotes = new ArrayList<>();
        int last = page*size;
        for(int i = last-size; i<last; i++) {
            try {
                Note note = notes.get(i);
                if (Objects.equals(note.getUser().getId(), id)) {
                    userNotes.add(notes.get(i));
                }
            } catch (Exception e) {
                break;
            }
        } if (!userNotes.isEmpty()) return userNotes;
        return null;
    }

    private User findUserById(Long id)
    {
        for (User u : users)
        {
            if (u.getId().equals(id)) return u;
        }
        return null;
    }

    private Ride findRideById(Long id)
    {
        for (Ride r : rides)
        {
            if (r.getId().equals(id)) return r;
        }
        return null;
    }
}
