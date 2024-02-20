package com.Myblog.Service;

import com.Myblog.Payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commmentDto);
    List<CommentDto> getCommentsByPostId(long postId);

    CommentDto getCommentsById(Long postId, Long commentId);

    List<CommentDto> getAllCommentsById();

    void deleteCommentById(Long postId, Long commentId);
}
