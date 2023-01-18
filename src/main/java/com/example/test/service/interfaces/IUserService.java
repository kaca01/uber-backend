package com.example.test.service.interfaces;

import com.example.test.domain.user.User;
import com.example.test.dto.AllDTO;
import com.example.test.dto.communication.MessageDTO;
import com.example.test.dto.communication.NoteDTO;
import com.example.test.dto.ride.RideDTO;
import com.example.test.dto.user.ChangePasswordDTO;
import com.example.test.dto.user.ResetPasswordDTO;
import com.example.test.dto.user.UserDTO;

import java.text.ParseException;
import java.util.List;

public interface IUserService {

    void changePassword(Long id, ChangePasswordDTO changePasswordDTO);

    void sendResetEmail(Long id);

    void resetEmail(Long id, ResetPasswordDTO resetPasswordDTO);

    List<RideDTO> getRides(Long id);

    List<UserDTO> get();

    List<MessageDTO> getMessages(Long id);

    MessageDTO insertMessage(Long id, MessageDTO requestMessage, User p);

    void block(Long id);

    void unblock(Long id);

    NoteDTO insertNote(Long id, NoteDTO requestNote) throws ParseException;

    AllDTO<NoteDTO> getNotes(Long id);

    User findByEmail(String email);
}
