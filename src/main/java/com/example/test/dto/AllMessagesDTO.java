package com.example.test.dto;

import java.util.ArrayList;

public class AllMessagesDTO {

    private Long totalCount;
    private ArrayList<MessageDTO> results;

    public AllMessagesDTO()
    {

    }

    //response
    public AllMessagesDTO(Long totalCount, ArrayList<MessageDTO> results) {
        this.totalCount = totalCount;
        this.results = results;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public ArrayList<MessageDTO> getResults() {
        return results;
    }

    public void setResults(ArrayList<MessageDTO> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "AllMessagesDTO{" +
                "totalCount=" + totalCount +
                ", results=" + results +
                '}';
    }
}
