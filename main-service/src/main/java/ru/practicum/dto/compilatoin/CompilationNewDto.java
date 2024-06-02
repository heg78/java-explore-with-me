package ru.practicum.dto.compilatoin;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompilationNewDto {

    private Boolean pinned;

    @NotBlank
    @Size(min = 1, max = 50)

    private String title;
    private Set<Long> events;
}