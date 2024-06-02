package ru.practicum.model.mappers;

import lombok.experimental.UtilityClass;
import ru.practicum.dto.compilatoin.CompilationDto;
import ru.practicum.dto.compilatoin.CompilationNewDto;
import ru.practicum.model.Compilation;

import java.util.stream.Collectors;

@UtilityClass
public class CompilationMapper {

    public CompilationDto toDto(Compilation compilation) {
        return CompilationDto.builder()
                .id(compilation.getId())
                .events(compilation.getEvents().stream()
                        .map(EventMapper::toEventShortDto)
                        .collect(Collectors.toSet()))
                .pinned(compilation.getPinned())
                .title(compilation.getTitle())
                .build();
    }

    public Compilation toCompilation(CompilationNewDto compilationDto) {
        return Compilation.builder()
                .pinned(compilationDto.getPinned())
                .title(compilationDto.getTitle())
                .build();
    }
}