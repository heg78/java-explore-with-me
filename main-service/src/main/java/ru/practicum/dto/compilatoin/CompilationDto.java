package ru.practicum.dto.compilatoin;

import lombok.*;
import ru.practicum.dto.event.EventShortDto;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompilationDto {

    private Long id;
    private Set<EventShortDto> events;
    private Boolean pinned;
    private String title;
}