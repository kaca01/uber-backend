package com.example.test.service.interfaces;

import com.example.test.domain.communication.Message;
import com.example.test.domain.communication.Note;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Passenger;
import com.example.test.domain.user.User;
import com.example.test.dto.AllDTO;
import com.example.test.dto.communication.MessageDTO;
import com.example.test.dto.communication.NoteDTO;
import com.example.test.dto.ride.RideDTO;
import com.example.test.dto.user.ChangePasswordDTO;
import com.example.test.dto.user.ResetPasswordDTO;
import com.example.test.dto.user.UserDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.ParseException;
import java.util.List;

public interface IUserService {

    User changePassword(Long id, ChangePasswordDTO changePasswordDTO);

    User sendResetEmail(Long id);

    User resetEmail(Long id, ResetPasswordDTO resetPasswordDTO);

    List<RideDTO> getRides(Long id, int page, int size, String sort, String from, String to);

    List<UserDTO> get(int page, int size);

    List<String> login(String email, String password);

    List<MessageDTO> getMessages(Long id);

    MessageDTO insertMessage(Long id, MessageDTO requestMessage, User p);

    Boolean block(Long id);

    Boolean unblock(Long id);

    NoteDTO insertNote(Long id, NoteDTO requestNote) throws ParseException;

    AllDTO<NoteDTO> getNotes(Long id, int page, int size);

    User findByEmail(String email);
}
