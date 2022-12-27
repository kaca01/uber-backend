package com.example.test.service.interfaces;

import com.example.test.domain.communication.Message;
import com.example.test.domain.communication.Note;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.User;
import com.example.test.dto.AllDTO;
import com.example.test.dto.communication.MessageDTO;
import com.example.test.dto.communication.NoteDTO;

import java.text.ParseException;
import java.util.List;

public interface IUserService {

    public List<Ride> getRides(Long id, int page, int size, String sort, String from, String to);

    public List<User> get(int page, int size);

    public List<String> login(String email, String password);

    public List<Message> getMessages(Long id);

    public Message insertMessage(Long id, MessageDTO requestMessage);

    public Boolean block(Long id);

    public Boolean unblock(Long id);

    public NoteDTO insertNote(Long id, NoteDTO requestNote) throws ParseException;

    public AllDTO<NoteDTO> getNotes(Long id, int page, int size);
}
