package com.example;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by student on 3/16/17.
 */
@Entity
@Data
@Table(name = "people")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;
    private String lastName;

}
