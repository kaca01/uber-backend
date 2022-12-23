package com.example.test.domain.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
//@PrimaryKeyJoinColumn(name = "id")
public class Admin extends User {

}
