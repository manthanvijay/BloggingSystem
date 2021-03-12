package com.wheelseye.Blogging.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="authors")
@Getter @Setter

public class Author implements Serializable
{
    @Id   // annotation used to specify the primary key of the table
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // autoincrementing the id value
    @Column(name = "author_id", unique = true, nullable = false)
    private Integer authorId;


    @Column(name="name")
    private String name;

    @Column(name = "dept")
    private String dept;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password")
    private String password;
}
