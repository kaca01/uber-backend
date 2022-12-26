package com.example.test.service;

import com.example.test.domain.communication.Message;
import com.example.test.dto.communication.PanicDTO;
import com.example.test.enumeration.MessageType;
import com.example.test.repository.communication.IMessageRepository;
import com.example.test.service.interfaces.IPanicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PanicService implements IPanicService{

    @Autowired
    private IMessageRepository messageRepository;

    @Override
    public List<PanicDTO> getAll()
    {
        List<Message> messages = messageRepository.findByType(MessageType.PANIC);
        // convert panics to DTOs
        List<PanicDTO> panicsDTO = new ArrayList<>();
        for (Message m : messages) {
            panicsDTO.add(new PanicDTO(m));
        }

        return panicsDTO;
    }

}
