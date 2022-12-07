package com.example.test.dto;

import java.util.ArrayList;

public class AllNotesDTO {
    private int totalCount;
    private ArrayList<NoteDTO> results;

    public AllNotesDTO(){}

    //response
    public AllNotesDTO(int totalCount, ArrayList<NoteDTO> results) {
        this.totalCount = totalCount;
        this.results = results;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public ArrayList<NoteDTO> getResults() {
        return results;
    }

    public void setResults(ArrayList<NoteDTO> results) {
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
