package com.sparta.final_project.controller;

import com.sparta.final_project.domain.Post;
import com.sparta.final_project.domain.PostRepository;
import com.sparta.final_project.domain.PostRequestDto;
import com.sparta.final_project.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;

    @GetMapping("/api/posts")
    public List<Post> getPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    @PostMapping("/api/posts")
    public Post createPost(@RequestBody PostRequestDto postRequestDto) {
        Post post = new Post(postRequestDto);
        postRepository.save(post);
        return post;
    }

    @DeleteMapping("api/posts/{id}")
    public Long deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
        return id;
    }

    @PutMapping("api/posts/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
        return postService.update(id, postRequestDto);
    }

    @GetMapping("api/posts/{id}")
    public Post readPost(@PathVariable Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다")
        );
    }
}