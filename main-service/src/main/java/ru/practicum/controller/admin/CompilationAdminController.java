package ru.practicum.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.compilatoin.CompilationDto;
import ru.practicum.dto.compilatoin.CompilationNewDto;
import ru.practicum.dto.compilatoin.CompilationUpdateDto;
import ru.practicum.service.CompilationService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/compilations")
public class CompilationAdminController {

    private final CompilationService compilationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompilationDto addCompilationByAdmin(@RequestBody @Valid CompilationNewDto compilationDto) {
        return compilationService.addCompilation(compilationDto);
    }

    @PatchMapping("/{compId}")
    public CompilationDto updateCompilationByAdmin(@RequestBody @Valid CompilationUpdateDto update,
                                                   @PathVariable Long compId) {
        return compilationService.updateCompilation(compId, update);
    }

    @DeleteMapping("/{compId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompilationByAdmin(@PathVariable Long compId) {
        compilationService.deleteCompilation(compId);
    }
}