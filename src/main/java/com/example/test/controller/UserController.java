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
    public ResponseEntity<AllRidesDTO> getRides(@PathVariable int id, @RequestParam int page, @RequestParam int size,
                                                @RequestParam String sort, @RequestParam String from,
                                                @RequestParam String to) {

        Pageable pageable = (Pageable) PageRequest.of(page, size, Sort.by(sort));
        List<Ride> rides = service.getRides((long) id, pageable, from, to);

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
    public ResponseEntity<AllUsersDTO> get(@RequestParam int page, @RequestParam int size) {

        Pageable pageable = (Pageable) PageRequest.of(page, size);
        Page<User> users = service.get(pageable);

        List<UserDTO> usersDTO = new ArrayList<>();
        for(User user : users) {
            usersDTO.add(new UserDTO(user));
        }
        return new ResponseEntity<>(new AllUsersDTO(usersDTO.size(), usersDTO), HttpStatus.OK);
    }

    // TODO
    // login
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> login(@RequestBody LoginDTO loginDTO) throws Exception {

        Boolean success = service.login(loginDTO.getEmail(), loginDTO.getPassword());

        if(success == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Boolean>(success, HttpStatus.CREATED);
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

        Message message = new Message(messageDTO);
        message = service.insertMessage((long) id, message);

        if(message == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // todo za 400
        return new ResponseEntity<>(new MessageDTO(message), HttpStatus.CREATED);
    }

    // Blocking the user from the part of the administrator
    @PutMapping(value = "/user/{id}/block", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> block(@PathVariable int id) throws Exception {

        Boolean blockedUser = service.block((long) id);

        if (blockedUser == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(blockedUser, HttpStatus.OK);
    }

    // Unblocking user from the administrator
    @PutMapping(value = "/user/{id}/unblock", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> unblock(@PathVariable int id) throws Exception {

        Boolean unblockedUser = service.unblock((long) id);

        if (unblockedUser == null) {
            return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Boolean>(unblockedUser, HttpStatus.OK);
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
        return new ResponseEntity<>(new NoteDTO(note), HttpStatus.CREATED);
    }

    // Getting notes for the user
    @GetMapping(value ="/user/{id}/note", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllNotesDTO> getNotes(@PathVariable int id, @RequestParam int page,
                                                @RequestParam int size) {

        Pageable pageable = (Pageable) PageRequest.of(page, size);
        List<Note> notes = service.getNotes((long) id, pageable);

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
