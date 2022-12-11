package com.example.test.dto.communication;

import com.example.test.domain.communication.Rejection;

public class RejectionDTO {

    private String reason;
    private String timeOfRejection;

    public RejectionDTO() {

    }

    public RejectionDTO(Rejection rejection) {
        this.reason = rejection.getReason();
        if (rejection.getTimeOfRejection() == null){
            this.timeOfRejection = null;
        }
        else
            this.timeOfRejection = rejection.getTimeOfRejection().toString();
    }

    public RejectionDTO(String reason, String timeOfRejection) {
        this.reason = reason;
        this.timeOfRejection = timeOfRejection;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTimeOfRejection() {
        return timeOfRejection;
    }

    public void setTimeOfRejection(String timeOfRejection) {
        this.timeOfRejection = timeOfRejection;
    }
}
