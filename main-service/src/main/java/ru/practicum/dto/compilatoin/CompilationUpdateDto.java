package ru.practicum.dto.compilatoin;

import lombok.*;

import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompilationUpdateDto {

    private Long id;
    private Set<Long> events;
    private Boolean pinned;

    @Size(max = 50)
    private String title;
}