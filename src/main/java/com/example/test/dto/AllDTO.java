package com.example.test.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import java.util.List;

@Getter @Setter
public class AllDTO<T> {

    private int totalCount;

    private List<T> results;

    public AllDTO(int totalCount, List<T> results) {
        this.totalCount = totalCount;
        this.results = results;
    }
}
