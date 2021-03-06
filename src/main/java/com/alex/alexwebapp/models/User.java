package com.alex.alexwebapp.models;

import lombok.Data;

import javax.persistence.*;

import javax.validation.constraints.NotNull;

// TODO: delete (for now, keep for reference)
@Data
@Entity
@Table(name = "users")
public class User {

    // An autogenerated id (unique for each user in the db)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String email;

    @NotNull
    private String name;

    // Public methods

    public User() { }

    public User(long id) {
        this.id = id;
    }

    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
