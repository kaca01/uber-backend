package com.example.test.service;

import com.example.test.domain.communication.Message;
import com.example.test.service.interfaces.IPanicService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PanicService implements IPanicService{

    @Override
    public Collection<Message> getAll() {
        return null;
    }
}
