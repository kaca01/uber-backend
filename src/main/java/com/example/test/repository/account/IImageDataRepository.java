package com.example.test.repository.account;

import com.example.test.domain.account.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IImageDataRepository extends JpaRepository<ImageData, Long> {

    ImageData findByUserId(Long id);
    ImageData findByName(String name);
}
