package com.example.test.domain.business;


import com.example.test.dto.business.WorkingHourDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.jdbc.Work;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class WorkingHour {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "start", nullable = false)
    @PastOrPresent
    private Date start;
    @Column(name = "end", nullable = false)
    @PastOrPresent
    private Date end;
}
