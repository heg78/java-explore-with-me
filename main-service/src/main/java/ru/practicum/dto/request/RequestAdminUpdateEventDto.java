package ru.practicum.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practicum.model.enums.EventAdminState;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestAdminUpdateEventDto extends RequestUpdateEventDto {

    private EventAdminState stateAction;
}