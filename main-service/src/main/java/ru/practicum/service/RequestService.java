package ru.practicum.service;

import ru.practicum.dto.request.RequestParticipationDto;

import java.util.List;

public interface RequestService {
    RequestParticipationDto addRequest(Long userId, Long eventId);

    List<RequestParticipationDto> getRequestsByUserId(Long userId);

    RequestParticipationDto cancelRequest(Long userId, Long requestId);
}