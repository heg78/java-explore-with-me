package ru.practicum.dto.category;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryNewDto {

    @NotBlank
    @Size(min = 1, max = 50)
    private String name;
}