package com.example.test.service.interfaces;

import com.example.test.domain.communication.Message;
import com.example.test.domain.communication.Note;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.User;

import java.util.Collection;

public interface IUserService {

    public Collection<Ride> getRides(Long id);

    public User get();

    public Boolean login(String email, String password);

    public Collection<Message> getMessages(Long id);

    public Message insertMessage(Long id, Message requestMessage);

    public Boolean block(Long id);

    public Boolean unblock(Long id);

    public Note insertNote(Long id, Note requestNote);

    public Collection<Note> getNotes(Long id);
}
