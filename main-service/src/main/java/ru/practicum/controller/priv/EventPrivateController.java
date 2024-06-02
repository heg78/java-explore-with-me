package ru.practicum.controller.priv;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.event.EventFullDto;
import ru.practicum.dto.event.EventNewDto;
import ru.practicum.dto.event.EventShortDto;
import ru.practicum.dto.request.RequestParticipationDto;
import ru.practicum.dto.request.RequestUpdateEventStatusDto;
import ru.practicum.dto.request.RequestUpdateResultEventStatusDto;
import ru.practicum.dto.request.RequestUserUpdateEventDto;
import ru.practicum.service.EventService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(path = "/users/{userId}/events")
public class EventPrivateController {

    private final EventService eventService;

    @GetMapping
    public List<EventShortDto> getEventsByUserId(@PathVariable(value = "userId") @Min(1) Long userId,
                                                 @RequestParam(value = "from", defaultValue = "0")
                                                 @PositiveOrZero Integer from,
                                                 @RequestParam(value = "size", defaultValue = "10")
                                                 @Positive Integer size) {
        return eventService.getEventsByUserId(userId, from, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventFullDto addEvent(@PathVariable(value = "userId") @Min(1) Long userId,
                                 @RequestBody @Valid EventNewDto input) {
        return eventService.addEvent(userId, input);
    }

    @GetMapping("/{eventId}")
    public EventFullDto getEventByUserIdAndEventId(@PathVariable(value = "userId") @Min(1) Long userId,
                                                   @PathVariable(value = "eventId") @Min(1) Long eventId) {
        return eventService.getEventByUserIdAndEventId(userId, eventId);
    }

    @PatchMapping("/{eventId}")
    public EventFullDto updateEventByUserIdAndEventId(@PathVariable(value = "userId") @Min(0) Long userId,
                                                      @PathVariable(value = "eventId") @Min(0) Long eventId,
                                                      @RequestBody @Valid RequestUserUpdateEventDto inputUpdate) {
        return eventService.updateEventByUserIdAndEventId(userId, eventId, inputUpdate);
    }

    @GetMapping("/{eventId}/requests")
    public List<RequestParticipationDto> getAllRequestByEventFromOwner(@PathVariable(value = "userId") @Min(1) Long userId,
                                                                       @PathVariable(value = "eventId") @Min(1) Long eventId) {
        return eventService.getAllRequestByEventFromOwner(userId, eventId);
    }

    @PatchMapping("/{eventId}/requests")
    public RequestUpdateResultEventStatusDto updateStatusRequest(@PathVariable(value = "userId") @Min(1) Long userId,
                                                                 @PathVariable(value = "eventId") @Min(1) Long eventId,
                                                                 @RequestBody RequestUpdateEventStatusDto inputUpdate) {
        return eventService.updateStatusRequest(userId, eventId, inputUpdate);
    }
}