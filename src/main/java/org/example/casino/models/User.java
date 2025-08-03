package org.example.casino.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="users")
public class User {
    public User() {

    }

    public User(String firstname, String lastname, String email, String password, String username, int age) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.username = username;
        this.age = age;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String firstname;

    @Column
    String lastname;

    @Column
    String email;

    @Column
    String password;

    @Column
    String username;

    @Column
    int age;
}
