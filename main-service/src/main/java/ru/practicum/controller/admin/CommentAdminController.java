package ru.practicum.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.model.Comment;
import ru.practicum.service.CommentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/comments")
public class CommentAdminController {

    private final CommentService commentService;

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long commentId) {
        commentService.deleteByAdmin(commentId);
    }

    @GetMapping("/search")
    public List<Comment> search(@RequestParam(name = "text") String text,
                                @RequestParam(value = "from", defaultValue = "0") Integer from,
                                @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return commentService.search(text, from, size);
    }
}