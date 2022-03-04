package com.indir.async.app.mapper;

import com.indir.async.app.dto.UserDto;
import com.indir.async.app.entity.User;

public class UserMapper {

    public static UserDto toDto(User user) {

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getFirstName());
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setLastUpdate(user.getLastUpdate());

        return userDto;
    }
}
