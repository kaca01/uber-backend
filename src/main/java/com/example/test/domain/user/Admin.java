package com.example.test.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Admin{
    private Long id;
    private String username;
    private String password;
    private String name;
    private String lastname;
    private String profilePicture;

    public Admin(Long id, String username, String password, String name, String lastname, String profilePicture) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.profilePicture = profilePicture;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                '}';
    }
}
