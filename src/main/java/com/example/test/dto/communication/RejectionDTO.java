package com.example.test.dto.communication;

import com.example.test.domain.communication.Rejection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class RejectionDTO {

    private String reason;
    private String timeOfRejection;


    public RejectionDTO(Rejection rejection) {
        if (rejection != null) {
            this.reason = rejection.getReason();
        }
        else this.reason = null;
        setTime(rejection);
    }

    public RejectionDTO(String reason, String timeOfRejection) {
        this.reason = reason;
        this.timeOfRejection = timeOfRejection;
    }

    public void setTime(Rejection rejection) {
        if (rejection == null) this.timeOfRejection = null;
        else this.timeOfRejection = rejection.getTimeOfRejection().toString();
    }

}
