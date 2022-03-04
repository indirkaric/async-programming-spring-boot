package com.indir.async.app.dto;


import lombok.Data;

import java.util.Date;

@Data
public class UserDto {

    private Long id;
    private String email;
    private Date createdAt;
    private Date lastUpdate;
    private String firstName;
    private String lastName;
}
