package com.example.test.controller;

import com.example.test.domain.user.User;
import com.example.test.dto.AllDTO;
import com.example.test.dto.communication.MessageDTO;
import com.example.test.dto.communication.NoteDTO;
import com.example.test.dto.ride.RideDTO;
import com.example.test.dto.user.*;
import com.example.test.security.TokenUtils;
import com.example.test.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private IUserService service;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private AuthenticationManager authenticationManager;


    // Change password of a user
    @PutMapping(value = "/user/{id}/changePassword", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> changePassword(@PathVariable Long id, @RequestBody ChangePasswordDTO changePasswordDTO)
    {
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    // Reset password of user
    @GetMapping(value = "/user/{id}/resetPassword", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> sendResetEmail(@PathVariable Long id)
    {
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    // Change password of a user with the reset code
    @PutMapping(value = "/user/{id}/resetPassword", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> resetPassword(@PathVariable Long id, @RequestBody ResetPasswordDTO resetPasswordDTO)
    {
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    // Rides of the user
    @GetMapping(value ="/user/{id}/ride", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllDTO<RideDTO>> getRides(@PathVariable Long id,
                                           @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size,
                                           @RequestParam String sort,
                                           @RequestParam String from,
                                           @RequestParam String to)
    {
        List<RideDTO> rides = service.getRides(id, page, size, sort, from, to);

        if(rides == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // todo za 400
        return new ResponseEntity<>(new AllDTO<>(rides.size(), rides), HttpStatus.OK);
    }

    // Getting multiple of them for the reason of showing a list
    @GetMapping(value ="/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllDTO<UserDTO>> get(@RequestParam(defaultValue = "1") int page,
                                               @RequestParam(defaultValue = "10") int size)
    {
        List<UserDTO> users = service.get(page, size);
        return new ResponseEntity<>(new AllDTO<>(users.size(), users), HttpStatus.OK);
    }


    // login
    @PostMapping(value = "/user/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserTokenState> login(@RequestBody LoginDTO loginDTO) throws Exception
    {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getEmail(), loginDTO.getPassword()));

        // Ukoliko je autentifikacija uspesna, ubaci korisnika u trenutni security kontekst
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Kreiraj token za tog korisnika
        User user = (User) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user.getEmail());
        int expiresIn = tokenUtils.getExpiredIn();

        // Vrati token kao odgovor na uspesnu autentifikaciju
        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
    }


    // Returns a list of user messages
    @GetMapping(value ="/user/{id}/message", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllDTO<MessageDTO>> getMessages(@PathVariable int id)
    {
        List<MessageDTO> messages = service.getMessages((long) id);
        if(messages == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // todo za 400
        return new ResponseEntity<>(new AllDTO<>(messages.size(), messages), HttpStatus.OK);
    }


    // Send a message to the user
    @PostMapping(value = "/user/{id}/message", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageDTO> insertMessage(@PathVariable int id, @RequestBody MessageDTO messageDTO)
            throws Exception
    {
        MessageDTO message = service.insertMessage((long) id, messageDTO);
        if(message == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // todo za 400
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    // Blocking the user from the part of the administrator
    @PutMapping(value = "/user/{id}/block", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> block(@PathVariable int id) throws Exception
    {
        Boolean blockedUser = service.block((long) id);
        if (!blockedUser) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // todo 400
        return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
    }

    // Unblocking user from the administrator
    @PutMapping(value = "/user/{id}/unblock", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> unblock(@PathVariable int id) throws Exception
    {
        Boolean unblockedUser = service.unblock((long) id);
        if (!unblockedUser) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
    }

    // Creating note
    @PostMapping(value = "/user/{id}/note", consumes = MediaType.APPLICATION_JSON_VALUE,
                                               produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<NoteDTO> insertNote(@PathVariable int id, @RequestBody NoteDTO requestNote) throws Exception
    {
        NoteDTO noteDTO = service.insertNote((long) id, requestNote);
        if(noteDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // todo za 400
        return new ResponseEntity<>(noteDTO, HttpStatus.OK);
    }

    // Getting notes for the user
    @GetMapping(value ="/user/{id}/note", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AllDTO<NoteDTO>> getNotes(@PathVariable int id, @RequestParam int page,
                                                    @RequestParam int size)
    {
        AllDTO<NoteDTO> noteDTOS = service.getNotes((long) id, page, size);
        if(noteDTOS == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // todo za 400
        return new ResponseEntity<>(noteDTOS, HttpStatus.OK);
    }

    @GetMapping("/whoami")
//    @PreAuthorize("hasRole('USER')")
    public User user(Principal user) {
        return this.service.findByEmail(user.getName());
    }
}
