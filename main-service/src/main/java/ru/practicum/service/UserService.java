package ru.practicum.service;

import ru.practicum.dto.request.RequestNewUserDto;
import ru.practicum.dto.user.UserDto;

import java.util.List;

public interface UserService {
    UserDto addNewUser(RequestNewUserDto requestNewUserDto);

    void deleteUser(Long userId);

    List<UserDto> getListUsers(List<Long> ids, Integer from, Integer size);
}