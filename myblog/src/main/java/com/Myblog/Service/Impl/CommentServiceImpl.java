package com.Myblog.Service.Impl;

import com.Myblog.Entity.Comment;
import com.Myblog.Entity.Post;
import com.Myblog.Exception.ResourceNotFound;
import com.Myblog.Payload.CommentDto;
import com.Myblog.Repository.CommentRepository;
import com.Myblog.Repository.PostRepository;
import com.Myblog.Service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
private CommentRepository commentRepository;
private PostRepository postRepository;
private ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commmentDto) {
        Comment comment = mapToEntity(commmentDto);
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFound("Post Not Found with id" + postId)
        );
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        CommentDto dto = mapToDto(savedComment);
        return dto;
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFound("Post Not Found with id" + postId)
        );
        List<Comment> comments = commentRepository.findByPostId(postId);
        List<CommentDto> commentDtos = comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
        return commentDtos;
    }

    @Override
    public CommentDto getCommentsById(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFound("Post Not Found with id" + postId)
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFound("Coment Not Found with id" + commentId)
        );
       CommentDto commentDto= mapToDto(comment);
        return commentDto;
    }

    @Override
    public List<CommentDto> getAllCommentsById() {
        List<Comment> comments = commentRepository.findAll();
        return  comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());

    }

    @Override
    public void deleteCommentById(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFound("Post Not Found with id" + postId)
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFound("Coment Not Found with id" + commentId)
        );
        commentRepository.deleteById(commentId);
    }

    private CommentDto mapToDto(Comment savedComment) {
        CommentDto dto = mapper.map(savedComment, CommentDto.class);
        return dto;
    }
    private Comment mapToEntity(CommentDto commmentDto) {
        Comment comment = mapper.map(commmentDto, Comment.class);
        return comment;

    }



}
