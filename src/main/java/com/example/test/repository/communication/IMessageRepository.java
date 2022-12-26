package com.example.test.repository.communication;

import com.example.test.domain.communication.Message;
import com.example.test.enumeration.MessageType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMessageRepository extends JpaRepository<Message, Long> {

    public List<Message> findByType(MessageType type);
}
