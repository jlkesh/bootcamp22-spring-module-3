package dev.jlkeesh.springadvanced.post;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAll() {
        return ResponseEntity.ok(postService.findAll());
    }

    @PostMapping
    public ResponseEntity<Post> create(@RequestBody PostCreateDTO dto) {
        return ResponseEntity.status(201).body(postService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostDTO(@PathVariable Integer id) {
        return ResponseEntity.ok(postService.getPostDetails(id));
    }

}
