package ru.practicum.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.event.EventFullDto;
import ru.practicum.dto.event.EventSearchParamsByAdminDto;
import ru.practicum.dto.request.RequestAdminUpdateEventDto;
import ru.practicum.service.EventService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping(path = "/admin/events")
@RequiredArgsConstructor
@Validated
public class EventAdminController {

    private final EventService eventService;

    @GetMapping
    public List<EventFullDto> searchEventsByAdmin(@Valid EventSearchParamsByAdminDto eventSearchParamsByAdminDto) {
        return eventService.getAllEventFromAdmin(eventSearchParamsByAdminDto);
    }

    @PatchMapping("/{eventId}")
    public EventFullDto updateEventByAdmin(@PathVariable(value = "eventId") @Min(1) Long eventId,
                                           @RequestBody @Valid RequestAdminUpdateEventDto inputUpdate) {
        return eventService.updateEventFromAdmin(eventId, inputUpdate);
    }
}