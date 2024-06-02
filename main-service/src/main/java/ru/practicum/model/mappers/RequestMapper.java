package ru.practicum.model.mappers;

import lombok.experimental.UtilityClass;
import ru.practicum.dto.request.RequestParticipationDto;
import ru.practicum.model.Request;

@UtilityClass
public class RequestMapper {

    public RequestParticipationDto toParticipationRequestDto(Request request) {
        return RequestParticipationDto.builder()
                .id(request.getId())
                .event(request.getEvent().getId())
                .created(request.getCreated())
                .requester(request.getId())
                .status(request.getStatus())
                .build();
    }

    public Request toRequest(RequestParticipationDto requestParticipationDto) {
        return Request.builder()
                .id(requestParticipationDto.getId())
                .event(null)
                .created(requestParticipationDto.getCreated())
                .requester(null)
                .status(requestParticipationDto.getStatus())
                .build();
    }
}