package ru.practicum.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.dto.comment.CommentDto;
import ru.practicum.dto.comment.NewCommentDto;
import ru.practicum.dto.comment.UpdateCommentDto;
import ru.practicum.exception.NotFoundException;
import ru.practicum.exception.UncorrectedParametersException;
import ru.practicum.model.Comment;
import ru.practicum.model.Event;
import ru.practicum.model.User;
import ru.practicum.model.enums.EventStatus;
import ru.practicum.model.mappers.CommentMapper;
import ru.practicum.repository.CommentRepository;
import ru.practicum.repository.EventRepository;
import ru.practicum.repository.UserRepository;
import ru.practicum.service.CommentService;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Override
    @Transactional
    public CommentDto patch(Long userId, Long commentId, UpdateCommentDto updateCommentDto) {
        User user = checkUser(userId);
        Comment comment = checkComment(commentId);
        checkAuthorComment(user, comment);
        LocalDateTime updateTime = LocalDateTime.now();

        if (updateTime.isAfter(comment.getCreated().plusHours(1L))) {
            throw new UncorrectedParametersException("Невозможно отредактировать сообщение");
        }

        comment.setText(updateCommentDto.getText());
        comment.setLastUpdatedOn(updateTime);
        return CommentMapper.toCommentDto(commentRepository.save(comment));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDto> get(Long userId) {
        checkUser(userId);
        List<Comment> commentList = commentRepository.findByAuthor_Id(userId);
        return commentList.stream().map(CommentMapper::toCommentDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Comment getByUserAndCommentId(Long userId, Long commentId) {
        checkUser(userId);
        return commentRepository.findByAuthor_IdAndId(userId, commentId).orElseThrow(() -> new NotFoundException(
                String.format("У пользователя id=%d  не найден комментарий id=%d", userId, commentId)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getCommentEvent(Long eventId, Integer from, Integer size) {
        Event event = checkEvent(eventId);
        PageRequest pageable = PageRequest.of(from / size, size);
        return commentRepository.findAllByEvent_Id(eventId, pageable);

    }

    @Override
    public void delete(Long userId, Long commentId) {
        User user = checkUser(userId);
        Comment comment = checkComment(commentId);
        checkAuthorComment(user, comment);
        commentRepository.deleteById(commentId);
    }

    @Override
    public void deleteByAdmin(Long commentId) {
        Comment comment = checkComment(commentId);
        commentRepository.deleteById(commentId);
    }

    @Override
    @Transactional
    public List<Comment> search(String text, Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from / size, size);
        if (text.isBlank()) {
            return Collections.emptyList();
        }
        return commentRepository.search(text, pageable);
    }

    @Transactional
    @Override
    public CommentDto create(Long userId, Long eventId, NewCommentDto commentDto) {
        Event event = checkEvent(eventId);
        User user = checkUser(userId);
        if (!event.getEventStatus().equals(EventStatus.PUBLISHED)) {
            throw new UncorrectedParametersException("Невозможно добавить комментарий к PUBLISHED");
        }
        return CommentMapper.toCommentDto(commentRepository.save(CommentMapper.toComment(commentDto, event, user)));
    }

    private Event checkEvent(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Событие id=%d  не найдено", id)));
    }

    private User checkUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Пользователь id=%d  не найден", id)));
    }

    private Comment checkComment(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Комментарий id=%d  не найден", id)));
    }

    private void checkAuthorComment(User user, Comment comment) {
        if (!comment.getAuthor().equals(user)) {
            throw new UncorrectedParametersException("Пользователь не автор комментария");
        }
    }
}