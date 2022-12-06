package com.example.test.controller;

import com.example.test.domain.communication.Message;
import com.example.test.domain.communication.Note;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.User;
import com.example.test.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private IUserService service;

    @GetMapping(value ="/user/{id}/ride", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Ride>> getRides(@PathVariable Long id) {
        Collection<Ride> rides = service.getRides(id);

        if(rides == null) {
            return new ResponseEntity<Collection<Ride>>(HttpStatus.NOT_FOUND);
        }
        // todo za 400
        return new ResponseEntity<Collection<Ride>>(rides, HttpStatus.OK);
    }

    @GetMapping(value ="/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> get() {
        User user = service.get();
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> login(@RequestBody String email, @RequestBody String password) throws Exception {
        Boolean success = service.login(email, password);

        if(success == null) {
            return new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Boolean>(success, HttpStatus.CREATED);
    }

    @GetMapping(value ="/user/{id}/message", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Message>> getMessages(@PathVariable Long id) {
        Collection<Message> messages = service.getMessages(id);

        if(messages == null) {
            return new ResponseEntity<Collection<Message>>(HttpStatus.NOT_FOUND);
        }
        // todo za 400
        return new ResponseEntity<Collection<Message>>(messages, HttpStatus.OK);
    }

    @PostMapping(value = "/user/{id}/message", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> insertMessage(@PathVariable Long id, @RequestBody Message requestMessage) throws Exception {
        Message message = service.insertMessage(id, requestMessage);

        if(message == null) {
            return new ResponseEntity<Message>(HttpStatus.NOT_FOUND);
        }
        // todo za 400
        return new ResponseEntity<Message>(message, HttpStatus.CREATED);
    }

    @PutMapping(value = "/user/{id}/block", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> block(@PathVariable Long id) throws Exception {
        Boolean blockedUser = service.block(id);

        if (blockedUser == null) {
            return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Boolean>(blockedUser, HttpStatus.OK);
    }

    @PutMapping(value = "/user/{id}/unblock", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> unblock(@PathVariable Long id) throws Exception {
        Boolean unblockedUser = service.unblock(id);

        if (unblockedUser == null) {
            return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Boolean>(unblockedUser, HttpStatus.OK);
    }

    @PostMapping(value = "/user/{id}/note", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Note> insertNote(@PathVariable Long id, @RequestBody Note requestNote) throws Exception {
        Note note = service.insertNote(id, requestNote);

        if(note == null) {
            return new ResponseEntity<Note>(HttpStatus.NOT_FOUND);
        }
        // todo za 400
        return new ResponseEntity<Note>(note, HttpStatus.CREATED);
    }

    @GetMapping(value ="/user/{id}/note", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Note>> getNotes(@PathVariable Long id) {
        Collection<Note> notes = service.getNotes(id);

        if(notes == null) {
            return new ResponseEntity<Collection<Note>>(HttpStatus.NOT_FOUND);
        }
        // todo za 400
        return new ResponseEntity<Collection<Note>>(notes, HttpStatus.OK);
    }
}
