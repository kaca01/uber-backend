package com.example.test.dto.communication;

import java.util.List;

public class AllMessagesDTO {

    private int totalCount;
    private List<MessageDTO> results;

    public AllMessagesDTO()
    {

    }

    //response
    public AllMessagesDTO(int totalCount, List<MessageDTO> results) {
        this.totalCount = totalCount;
        this.results = results;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<MessageDTO> getResults() {
        return results;
    }

    public void setResults(List<MessageDTO> results) {
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
