package com.indir.async.app.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

@Data
@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(unique = true)
    @Email
    private String email;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate = new Date();

    private String firstName;
    private String lastName;

}
