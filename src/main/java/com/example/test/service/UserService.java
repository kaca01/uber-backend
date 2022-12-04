package com.example.test.service;

import com.example.test.domain.communication.Message;
import com.example.test.domain.communication.Note;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.User;
import com.example.test.service.interfaces.IUserService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService implements IUserService {

    @Override
    public Collection<Ride> getRides(Long id) {
        return null;
    }

    @Override
    public User get() {
        return null;
    }

    @Override
    public Boolean login() {
        return null;
    }

    @Override
    public Collection<Message> getMessages(Long id) {
        return null;
    }

    @Override
    public Message insertMessage(Long id) {
        return null;
    }

    @Override
    public Boolean block(Long id) {
        return null;
    }

    @Override
    public Boolean unblock(Long id) {
        return null;
    }

    @Override
    public Note insertNote(Long id) {
        return null;
    }

    @Override
    public Collection<Note> getNotes(Long id) {
        return null;
    }
}
