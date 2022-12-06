package com.example.test.dto;

import java.util.ArrayList;

public class AllUsersDTO {

    private int totalCount;
    private ArrayList<UserDTO> results;


    public AllUsersDTO() {
    }

    // all users
    public AllUsersDTO(int totalCount, ArrayList<UserDTO> results) {
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

    public ArrayList<UserDTO> getResults() {
        return results;
    }

    public void setResults(ArrayList<UserDTO> results) {
        this.results = results;
    }
}
