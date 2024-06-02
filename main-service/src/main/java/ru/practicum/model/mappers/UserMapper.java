package ru.practicum.model.mappers;

import lombok.experimental.UtilityClass;
import ru.practicum.dto.request.RequestNewUserDto;
import ru.practicum.dto.user.UserDto;
import ru.practicum.dto.user.UserShortDto;
import ru.practicum.model.User;

@UtilityClass
public class UserMapper {

    public User toUser(RequestNewUserDto requestNewUserDto) {
        return User.builder()
                .name(requestNewUserDto.getName())
                .email(requestNewUserDto.getEmail())
                .build();
    }

    public UserDto toUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public UserShortDto toUserShortDto(User user) {
        return UserShortDto.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }
}