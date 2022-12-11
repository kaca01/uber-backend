package com.example.test.controller;

import com.example.test.domain.communication.Message;
import com.example.test.domain.communication.Note;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.User;
import com.example.test.dto.*;
import com.example.test.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private IUserService service;

    // Rides of the user
    @GetMapping(value ="/user/{id}/ride", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllRidesDTO> getRides(@PathVariable int id,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size,
                                                @RequestParam String sort,
                                                @RequestParam String from,
                                                @RequestParam String to) {

        List<Ride> rides = service.getRides((long) id, page, size, sort, from, to);

        if(rides == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<RideDTO> ridesDTO = new ArrayList<>();
        for (Ride ride : rides) {
            ridesDTO.add(new RideDTO(ride));
        }
        // todo za 400
        return new ResponseEntity<>(new AllRidesDTO(ridesDTO.size(), ridesDTO), HttpStatus.OK);
    }

    // Getting multiple of them for the reason of showing a list
    @GetMapping(value ="/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllUsersDTO> get(@RequestParam(defaultValue = "1") int page,
                                           @RequestParam(defaultValue = "10") int size) {

        List<User> users = service.get(page, size);

        List<UserDTO> usersDTO = new ArrayList<>();
        for(User user : users) {
            usersDTO.add(new UserDTO(user));
        }
        return new ResponseEntity<>(new AllUsersDTO(usersDTO.size(), usersDTO), HttpStatus.OK);
    }

    // login
    @PostMapping(value = "/user/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginDTO> login(@RequestBody LoginDTO loginDTO) throws Exception {

        List<String> tokens = service.login(loginDTO.getEmail(), loginDTO.getPassword());

        if(tokens == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new LoginDTO(tokens.get(0), tokens.get(1), true), HttpStatus.OK);
    }

    // Returns a list of user messages
    @GetMapping(value ="/user/{id}/message", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllMessagesDTO> getMessages(@PathVariable int id) {

        List<Message> messages = service.getMessages((long) id);

        if(messages == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<MessageDTO> messagesDTO = new ArrayList<>();
        for(Message message : messages) {
            messagesDTO.add(new MessageDTO(message));
        }
        // todo za 400
        return new ResponseEntity<>(new AllMessagesDTO(messagesDTO.size(), messagesDTO), HttpStatus.OK);
    }

    // Send a message to the user
    @PostMapping(value = "/user/{id}/message", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageDTO> insertMessage(@PathVariable int id, @RequestBody MessageDTO messageDTO) throws Exception {

        Message message = service.insertMessage((long) id, messageDTO);

        if(message == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // todo za 400
        return new ResponseEntity<>(new MessageDTO(message), HttpStatus.OK);
    }

    // Blocking the user from the part of the administrator
    @PutMapping(value = "/user/{id}/block", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> block(@PathVariable int id) throws Exception {

        Boolean blockedUser = service.block((long) id);
        if (!blockedUser) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // todo 400
        return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
    }

    // Unblocking user from the administrator
    @PutMapping(value = "/user/{id}/unblock", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> unblock(@PathVariable int id) throws Exception {

        Boolean unblockedUser = service.unblock((long) id);

        if (!unblockedUser) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
    }

    // Creating note
    @PostMapping(value = "/user/{id}/note", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NoteDTO> insertNote(@PathVariable int id, @RequestBody NoteDTO requestNote) throws Exception {

        Note note = new Note(requestNote);
        note = service.insertNote((long) id, note);

        if(note == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // todo za 400
        return new ResponseEntity<>(new NoteDTO(note), HttpStatus.OK);
    }

    // Getting notes for the user
    @GetMapping(value ="/user/{id}/note", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllNotesDTO> getNotes(@PathVariable int id, @RequestParam int page,
                                                @RequestParam int size) {

        List<Note> notes = service.getNotes((long) id, page, size);

        if(notes == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<NoteDTO> notesDTO = new ArrayList<>();
        for(Note note : notes) {
            notesDTO.add(new NoteDTO(note));
        }
        // todo za 400
        return new ResponseEntity<>(new AllNotesDTO(notesDTO.size(), notesDTO), HttpStatus.OK);
    }
}
