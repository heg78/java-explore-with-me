package ru.practicum.service;


import ru.practicum.dto.comment.CommentDto;
import ru.practicum.dto.comment.NewCommentDto;
import ru.practicum.dto.comment.UpdateCommentDto;
import ru.practicum.model.Comment;

import java.util.List;

public interface CommentService {
    CommentDto create(Long userId, Long eventId, NewCommentDto commentDto);

    CommentDto patch(Long userId, Long commentId, UpdateCommentDto updateCommentDto);

    List<CommentDto> get(Long userId);

    Comment getByUserAndCommentId(Long userId, Long commentId);

    List<Comment> getCommentEvent(Long eventId, Integer from, Integer size);

    void delete(Long userId, Long commentId);

    void deleteByAdmin(Long commentId);

    List<Comment> search(String text, Integer from, Integer size);
}