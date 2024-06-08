package ru.practicum.controller.priv;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.comment.CommentDto;
import ru.practicum.dto.comment.NewCommentDto;
import ru.practicum.dto.comment.UpdateCommentDto;
import ru.practicum.model.Comment;
import ru.practicum.service.CommentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/comments")
@RequiredArgsConstructor
public class CommentPrivateController {

    private final CommentService commentService;

    @PostMapping("/users/{userId}/events/{eventId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto add(@PathVariable Long userId,
                          @PathVariable Long eventId,
                          @Valid @RequestBody NewCommentDto newCommentDto) {
        return commentService.create(userId, eventId, newCommentDto);
    }

    @PatchMapping("/users/{userId}/{commentId}")
    public CommentDto patch(@PathVariable Long userId, @PathVariable Long commentId,
                            @Valid @RequestBody UpdateCommentDto updateCommentDto) {

        return commentService.patch(userId, commentId, updateCommentDto);
    }

    @GetMapping("/users/{userId}/comments")
    public List<CommentDto> get(@PathVariable Long userId) {
        return commentService.get(userId);
    }

    @DeleteMapping("/users/{userId}/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long userId, @PathVariable Long commentId) {
        commentService.delete(userId, commentId);
    }

    @GetMapping("/users/{userId}/{commentId}")
    public Comment getByUserAndCommentId(@PathVariable Long userId, @PathVariable Long commentId) {
        return commentService.getByUserAndCommentId(userId, commentId);
    }
}