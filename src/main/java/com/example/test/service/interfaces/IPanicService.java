package com.example.test.service.interfaces;

import com.example.test.domain.communication.Message;

import java.util.Collection;

public interface IPanicService {
    public Collection<Message> getAll();
}
