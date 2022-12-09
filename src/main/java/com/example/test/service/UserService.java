package com.example.test.service;

import com.example.test.domain.communication.Message;
import com.example.test.domain.communication.Note;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.User;
import com.example.test.service.interfaces.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Override
    public List<Ride> getRides(Long id, Pageable page, String from, String to) {
        // prvo se dobave sve voznje za datog korisnika, funkciji se prosledjuje page
        // svaka voznja se uporedjuje sa from, to
        return null;
    }

    @Override
    public Page<User> get(Pageable pageable) {
        return null;
    }

    @Override
    public Boolean login(String email, String password) {
        return null;
    }

    @Override
    public List<Message> getMessages(Long id) {
        return null;
    }

    @Override
    public Message insertMessage(Long id, Message requestMessage) {
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
    public Note insertNote(Long id, Note requestNote) {
        return null;
    }

    @Override
    public List<Note> getNotes(Long id, Pageable pageable) {
        return null;
    }
}
