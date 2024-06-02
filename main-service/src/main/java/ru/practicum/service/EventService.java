package ru.practicum.service;

import ru.practicum.dto.event.*;
import ru.practicum.dto.request.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface EventService {

    List<EventFullDto> getAllEventFromAdmin(EventSearchParamsByAdminDto eventSearchParamsByAdminDto);

    EventFullDto updateEventFromAdmin(Long eventId, RequestAdminUpdateEventDto inputUpdate);

    List<EventShortDto> getEventsByUserId(Long userId, Integer from, Integer size);

    EventFullDto addEvent(Long userId, EventNewDto input);

    EventFullDto getEventByUserIdAndEventId(Long userId, Long eventId);

    EventFullDto updateEventByUserIdAndEventId(Long userId, Long eventId, RequestUserUpdateEventDto inputUpdate);

    List<RequestParticipationDto> getAllRequestByEventFromOwner(Long userId, Long eventId);

    RequestUpdateResultEventStatusDto updateStatusRequest(Long userId, Long eventId, RequestUpdateEventStatusDto inputUpdate);

    List<EventShortDto> getAllEvents(EventSearchParamsDto eventSearchParamsDto, HttpServletRequest request);

    EventFullDto getEvent(Long eventId, HttpServletRequest request);
}