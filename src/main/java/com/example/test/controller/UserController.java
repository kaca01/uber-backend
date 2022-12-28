package com.example.test.controller;

import com.example.test.dto.AllDTO;
import com.example.test.dto.communication.MessageDTO;
import com.example.test.dto.communication.NoteDTO;
import com.example.test.dto.ride.RideDTO;
import com.example.test.dto.user.LoginDTO;
import com.example.test.dto.user.UserDTO;
import com.example.test.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private IUserService service;

    // Rides of the user
    // TODO : does not work for passengers id, works only for drivers
    @GetMapping(value ="/user/{id}/ride", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllDTO> getRides(@PathVariable Long id,
                                           @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size,
                                           @RequestParam String sort,
                                           @RequestParam String from,
                                           @RequestParam String to) {

        List<RideDTO> rides = service.getRides(id, page, size, sort, from, to);
        for (RideDTO ride : rides) System.out.println(ride);

        if(rides == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // todo za 400
        return new ResponseEntity<>(new AllDTO(rides.size(), rides), HttpStatus.OK);
    }

    // Getting multiple of them for the reason of showing a list
    @GetMapping(value ="/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllDTO> get(@RequestParam(defaultValue = "1") int page,
                                      @RequestParam(defaultValue = "10") int size) {

        List<UserDTO> users = service.get(page, size);
        return new ResponseEntity<>(new AllDTO(users.size(), users), HttpStatus.OK);
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
    public ResponseEntity<AllDTO> getMessages(@PathVariable int id) {

        List<MessageDTO> messages = service.getMessages((long) id);

        if(messages == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // todo za 400
        return new ResponseEntity<>(new AllDTO(messages.size(), messages), HttpStatus.OK);
    }

    // Send a message to the user
    @PostMapping(value = "/user/{id}/message", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageDTO> insertMessage(@PathVariable int id, @RequestBody MessageDTO messageDTO) throws Exception {

        MessageDTO message = service.insertMessage((long) id, messageDTO);

        if(message == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // todo za 400
        return new ResponseEntity<>(message, HttpStatus.OK);
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
    @PostMapping(value = "/user/{id}/note", consumes = MediaType.APPLICATION_JSON_VALUE,
                                               produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NoteDTO> insertNote(@PathVariable int id, @RequestBody NoteDTO requestNote) throws Exception {
        NoteDTO noteDTO = service.insertNote((long) id, requestNote);

        if(noteDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // todo za 400
        return new ResponseEntity<>(noteDTO, HttpStatus.OK);
    }

    // Getting notes for the user
    @GetMapping(value ="/user/{id}/note", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllDTO<NoteDTO>> getNotes(@PathVariable int id, @RequestParam int page,
                                                    @RequestParam int size) {

        AllDTO<NoteDTO> noteDTOS = service.getNotes((long) id, page, size);

        if(noteDTOS == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // todo za 400
        return new ResponseEntity<>(noteDTOS, HttpStatus.OK);
    }
}
