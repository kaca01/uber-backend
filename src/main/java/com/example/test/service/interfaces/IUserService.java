package com.example.test.service.interfaces;

import com.example.test.domain.user.Role;
import com.example.test.domain.user.User;
import com.example.test.dto.AllDTO;
import com.example.test.dto.communication.MessageDTO;
import com.example.test.dto.communication.NoteDTO;
import com.example.test.dto.ride.RideDTO;
import com.example.test.dto.user.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.List;

public interface IUserService {

    void changePassword(Long id, ChangePasswordDTO changePasswordDTO);

    void sendResetEmail(String email) throws MessagingException, UnsupportedEncodingException;

    void resetEmail(String email, ResetPasswordDTO resetPasswordDTO);

    List<RideDTO> getRides(Long id);

    List<UserDTO> get();

    void login(LoginDTO loginDTO);

    List<MessageDTO> getMessages(Long id);

    List<MessageDTO> getSupportMessages(Long id);

    MessageDTO insertMessage(Long id, MessageDTO requestMessage, User p);

    void block(Long id);

    void unblock(Long id);

    NoteDTO insertNote(Long id, NoteDTO requestNote) throws ParseException;

    AllDTO<NoteDTO> getNotes(Long id);

    User findByEmail(String email);

    void uploadImage(Long id, MultipartFile file) throws IOException;

    byte[] downloadImage(Long id);

    void deleteImage(Long id);

    Role getRole(Long id);
}
