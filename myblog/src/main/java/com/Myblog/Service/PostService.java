package com.Myblog.Service;

import com.Myblog.Payload.PostDto;
import com.Myblog.Payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto savePost(PostDto postDto);
    void deletePost(long id);
    PostDto updatePost(long id, PostDto postDto);
    PostDto getPost(long id);

   PostResponse getPosts(int pageNo, int pageSize, String sortBy, String sortDir);
}
