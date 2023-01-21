package com.example.test.controller;

import com.example.test.domain.user.User;
import com.example.test.dto.AllDTO;
import com.example.test.dto.communication.MessageDTO;
import com.example.test.dto.communication.NoteDTO;
import com.example.test.dto.ride.RideDTO;
import com.example.test.dto.user.*;
import com.example.test.exception.BadRequestException;
import com.example.test.exception.NotFoundException;
import com.example.test.repository.user.IUserRepository;
import com.example.test.security.TokenUtils;
import com.example.test.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {
    @Autowired
    private IUserService service;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    public BCryptPasswordEncoder passwordEncoder;

    // Change password of a user
    @PutMapping(value = "/user/{id}/changePassword", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> changePassword(@PathVariable Long id, @Valid @RequestBody ChangePasswordDTO changePasswordDTO)
    {
        service.changePassword(id, changePasswordDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Reset password of user
    @GetMapping(value = "/user/{id}/resetPassword", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> sendResetEmail(@PathVariable Long id)
    {
        service.sendResetEmail(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Change password of a user with the reset code
    @PutMapping(value = "/user/{id}/resetPassword", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> resetPassword(@PathVariable Long id, @RequestBody ResetPasswordDTO resetPasswordDTO)
    {
        service.resetEmail(id, resetPasswordDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Rides of the user
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value ="/user/{id}/ride", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllDTO<RideDTO>> getRides(@PathVariable Long id) {
        List<RideDTO> rides = service.getRides(id);
        return new ResponseEntity<>(new AllDTO<>(rides.size(), rides), HttpStatus.OK);
    }

    // Getting multiple of them for the reason of showing a list
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value ="/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllDTO<UserDTO>> get() {
        List<UserDTO> users = service.get();
        return new ResponseEntity<>(new AllDTO<>(users.size(), users), HttpStatus.OK);
    }

    // login
    @PostMapping(value = "/user/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserTokenState> login(@RequestBody LoginDTO loginDTO)
    {
        User check = userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(() -> new BadRequestException("Wrong username or password!"));
        if(!check.getEmail().equals(loginDTO.getEmail()) ||
        !passwordEncoder.matches(loginDTO.getPassword(), check.getPassword()))
            throw new BadRequestException("Wrong username or password!");
        
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getEmail(), loginDTO.getPassword()));

        // if authentication is successful, add user in current security context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Create tokens for that user
        User user = (User) authentication.getPrincipal();

        String access = tokenUtils.generateToken(user.getEmail());
        String refresh = tokenUtils.generateRefreshToken(user.getEmail());

        // return a token in response to successful authentication
        return ResponseEntity.ok(new UserTokenState(access, refresh));
    }

    @GetMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request) throws Exception { }

    // Returns a list of user messages
    @GetMapping(value ="/user/{id}/message", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllDTO<MessageDTO>> getMessages(@PathVariable int id) {
        List<MessageDTO> messages = service.getMessages((long) id);
        return new ResponseEntity<>(new AllDTO<>(messages.size(), messages), HttpStatus.OK);
    }

    @GetMapping(value ="/user/{id}/message/support", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllDTO<MessageDTO>> getSupportMessages(@PathVariable int id) {
        List<MessageDTO> messages = service.getSupportMessages((long) id);
        if(messages == null) messages = new ArrayList<>();
        return new ResponseEntity<>(new AllDTO<>(messages.size(), messages), HttpStatus.OK);
    }

    // Send a message to the user, sender is received from the token
    @PostMapping(value = "/user/{id}/message", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageDTO> insertMessage(@PathVariable int id, @Valid @RequestBody MessageDTO messageDTO)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User sender = userRepository.findByEmail(email).orElse(null);
        MessageDTO message = service.insertMessage((long) id, messageDTO, sender);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // Blocking the user from the part of the administrator
    @PutMapping(value = "/user/{id}/block", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> block(@PathVariable int id)
    {
        service.block((long) id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Unblocking user from the administrator
    @PutMapping(value = "/user/{id}/unblock", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> unblock(@PathVariable int id)
    {
        service.unblock((long) id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Creating note
    @PostMapping(value = "/user/{id}/note", consumes = MediaType.APPLICATION_JSON_VALUE,
                                               produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<NoteDTO> insertNote(@PathVariable int id, @Valid @RequestBody NoteDTO requestNote)
            throws Exception {
        NoteDTO noteDTO = service.insertNote((long) id, requestNote);
        return new ResponseEntity<>(noteDTO, HttpStatus.OK);
    }

    // Getting notes for the user
    @GetMapping(value ="/user/{id}/note", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AllDTO<NoteDTO>> getNotes(@PathVariable int id) {
        AllDTO<NoteDTO> noteDTOS = service.getNotes((long) id);
        return new ResponseEntity<>(noteDTOS, HttpStatus.OK);
    }

    @GetMapping("/currentUser")
    public User user(Principal user) {
        return this.service.findByEmail(user.getName());
    }
}
