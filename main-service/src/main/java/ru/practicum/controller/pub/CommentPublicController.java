package ru.practicum.controller.pub;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.model.Comment;
import ru.practicum.service.CommentService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping(path = "/comments")
public class CommentPublicController {

    private final CommentService commentService;

    @GetMapping("/{eventId}")
    public List<Comment> getCommentEvent(@PathVariable Long eventId,
                                         @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
                                         @RequestParam(defaultValue = "10") @Positive Integer size) {
        return commentService.getCommentEvent(eventId, from, size);
    }
}