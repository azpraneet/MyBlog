package com.Myblog.Controller;

import com.Myblog.Payload.PostDto;
import com.Myblog.Payload.PostResponse;
import com.Myblog.Service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    //http://localhost:8080/api/post
@PostMapping
    public ResponseEntity<?> savePost(@Valid @RequestBody  PostDto postDto, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    PostDto dto = postService.savePost(postDto);
    return new ResponseEntity<>(dto, HttpStatus.CREATED);
}
    //http://localhost:8080/api/post/1
@DeleteMapping("/{id}")
public ResponseEntity<String> deletePost(@PathVariable long id){
        postService.deletePost(id);
        return new ResponseEntity<>("post is Deleted", HttpStatus.OK );
}
    //http://localhost:8080/api/post/1
@PutMapping("/{id}")
public ResponseEntity<PostDto> updatePost(@PathVariable ("id") long id , @RequestBody PostDto postDto){
    PostDto dto = postService.updatePost(id, postDto);
    return new ResponseEntity<>(dto,HttpStatus.OK) ;
}
    //http://localhost:8080/api/post/1
@GetMapping("/{id}")
public ResponseEntity<PostDto> getPost(@PathVariable("id") long id){
    PostDto dto = postService.getPost(id);
    return new ResponseEntity<>(dto, HttpStatus.OK);
}
    //http://localhost:8080/api/post?pageNo=0&pageSize=5&sortBy=title
@GetMapping
public PostResponse getPosts(
        @RequestParam(value = "pageNo", defaultValue = "0", required = false)int pageNo,
        @RequestParam(value = "pageSize", defaultValue = "3", required = false)int pageSize,
        @RequestParam(value = "sortBy", defaultValue = "id", required = false)String sortBy,
        @RequestParam(value = "sortDir", defaultValue = "asc", required = false)String sortDir){
        PostResponse postResponse= postService.getPosts(pageNo,pageSize,sortBy,sortDir);
        return postResponse;
}
}
