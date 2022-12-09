package com.example.test.service.interfaces;

import com.example.test.domain.communication.Message;
import com.example.test.domain.communication.Note;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.User;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface IUserService {

    public List<Ride> getRides(Long id, Pageable page, String from, String to);

    public Page<User> get(Pageable pageable);

    public Boolean login(String email, String password);

    public List<Message> getMessages(Long id);

    public Message insertMessage(Long id, Message requestMessage);

    public Boolean block(Long id);

    public Boolean unblock(Long id);

    public Note insertNote(Long id, Note requestNote);

    public List<Note> getNotes(Long id, Pageable pageable);
}
