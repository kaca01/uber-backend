package com.example.test.dto;

import java.util.ArrayList;
import java.util.List;

public class AllNotesDTO {
    private int totalCount;
    private List<NoteDTO> results;

    public AllNotesDTO(){}

    //response
    public AllNotesDTO(int totalCount, List<NoteDTO> results) {
        this.totalCount = totalCount;
        this.results = results;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<NoteDTO> getResults() {
        return results;
    }

    public void setResults(List<NoteDTO> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "AllNotesDTO{" +
                "totalCount=" + totalCount +
                ", results=" + results +
                '}';
    }
}
