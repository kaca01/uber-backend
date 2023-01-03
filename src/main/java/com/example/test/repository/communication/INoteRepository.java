package com.example.test.repository.communication;

import com.example.test.domain.communication.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface INoteRepository extends JpaRepository<Note, Integer> {
    public List<Note> findByUserId(Long id);
}
