package com.streamworx.api2.controller;

import com.streamworx.api2.dto.PostList;
import com.streamworx.api2.repository.PostRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public PostList getAllPosts() {
        return new PostList(postRepository.findAll());
    }
}