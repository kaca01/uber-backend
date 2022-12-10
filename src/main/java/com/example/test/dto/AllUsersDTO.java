package com.example.test.dto;

import java.util.ArrayList;
import java.util.List;

public class AllUsersDTO {

    private int totalCount;
    private List<UserDTO> results;


    public AllUsersDTO() {
    }

    // all users
    public AllUsersDTO(int totalCount, List<UserDTO> results) {
        super();
        this.totalCount = totalCount;
        this.results = results;
    }


    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<UserDTO> getResults() {
        return results;
    }

    public void setResults(List<UserDTO> results) {
        this.results = results;
    }
}
