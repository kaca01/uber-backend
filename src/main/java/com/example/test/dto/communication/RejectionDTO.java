package com.example.test.dto.communication;

import com.example.test.domain.communication.Rejection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@Data
public class RejectionDTO {

    @Length(max = 500)
    @NotEmpty
    @NotNull
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
