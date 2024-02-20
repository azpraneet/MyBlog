package com.Myblog.Payload;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDto {
    private long id;
    @NotEmpty
    @Size(min=2, message = "Post title should be  2 character")
    private String title;
    @NotEmpty
    @Size(min=5, message = "Post Description should be  5 character")
    private String description;
    @NotEmpty
    @Size(min=4, message = "Post content should be  4 character")
    private String content;
}
