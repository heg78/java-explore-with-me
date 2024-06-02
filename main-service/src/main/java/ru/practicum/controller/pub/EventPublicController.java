package ru.practicum.controller.pub;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.dto.event.EventFullDto;
import ru.practicum.dto.event.EventSearchParamsDto;
import ru.practicum.dto.event.EventShortDto;
import ru.practicum.service.EventService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(path = "/events")
public class EventPublicController {

    private final EventService eventService;

    @GetMapping
    public List<EventShortDto> getAllEvents(@Valid EventSearchParamsDto eventSearchParamsDto,
                                            HttpServletRequest request) {
        return eventService.getAllEvents(eventSearchParamsDto, request);
    }

    @GetMapping("/{eventId}")
    public EventFullDto getEvent(@PathVariable(value = "eventId") @Min(1) Long eventId,
                                 HttpServletRequest request) {
        return eventService.getEvent(eventId, request);
    }
}