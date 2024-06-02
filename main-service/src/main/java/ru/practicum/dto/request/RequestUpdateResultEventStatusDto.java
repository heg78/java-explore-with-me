package ru.practicum.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestUpdateResultEventStatusDto {

    private List<RequestParticipationDto> confirmedRequests;
    private List<RequestParticipationDto> rejectedRequests;
}