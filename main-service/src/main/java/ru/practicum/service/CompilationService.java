package ru.practicum.service;

import ru.practicum.dto.compilatoin.CompilationDto;
import ru.practicum.dto.compilatoin.CompilationNewDto;
import ru.practicum.dto.compilatoin.CompilationUpdateDto;

import java.util.List;

public interface CompilationService {
    CompilationDto addCompilation(CompilationNewDto compilationDto);

    CompilationDto updateCompilation(Long compId, CompilationUpdateDto update);

    void deleteCompilation(Long compId);

    List<CompilationDto> getCompilations(Boolean pinned, Integer from, Integer size);

    CompilationDto findByIdCompilation(Long compId);
}