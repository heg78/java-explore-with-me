package ru.practicum.dto.event;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventCaseUpdatedStatusDto {

    private List<Long> listUpdateStatus;
    private List<Long> listProcessed;
}