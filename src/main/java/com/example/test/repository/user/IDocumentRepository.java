package com.example.test.repository.user;

import com.example.test.domain.user.Document;
import com.example.test.domain.user.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IDocumentRepository extends JpaRepository<Document, Integer> {

    public Document findById(Long id);

    public void deleteDocumentById(Long id);
    @Query("select d from Document d where d.driver  = ?1")
    public List<Document> findDocumentsByDriverId(Driver driver);

}
