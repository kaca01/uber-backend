package com.example.test.dto;

import java.util.ArrayList;

public class AllNotesDTO {
    private int totalCount;
    private ArrayList<Note> results;

    public AllNotesDTO(){}

    public AllNotesDTO(int totalCount, ArrayList<Note> results) {
        this.totalCount = totalCount;
        this.results = results;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public ArrayList<Note> getResults() {
        return results;
    }

    public void setResults(ArrayList<Note> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "AllNotesDTO{" +
                "totalCount=" + totalCount +
                ", results=" + results +
                '}';
    }

    private static class Note
    {
        private Long id;
        private String date;
        private String message;

        public Note(){};

        public Note(Long id, String date, String message)
        {
            this.id=id;
            this.date=date;
            this.message=message;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "Note{" +
                    "id=" + id +
                    ", date='" + date + '\'' +
                    ", message='" + message + '\'' +
                    '}';
        }
    }
}
