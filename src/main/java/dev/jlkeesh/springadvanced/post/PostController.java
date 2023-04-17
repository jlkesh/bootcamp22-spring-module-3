package dev.jlkeesh.springadvanced.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @PostMapping
    public ResponseEntity<Post> create(@RequestBody Post post) {
        return ResponseEntity.status(201).body(postRepository.save(post));
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAll() {
        return ResponseEntity.ok(postRepository.findAll());
    }

    @GetMapping("/{title}")
    public ResponseEntity<List<Post>> geTByTitle(@PathVariable String title) {
        return ResponseEntity.ok(postRepository.findAllByTitleRegex("^" + title + ".*"));
    }

    @GetMapping("/sort")
    public ResponseEntity<List<Post>> getSorted() {
        Sort sort = Sort.by(Sort.Order.asc("title"), Sort.Order.desc("createdAt"));
        return ResponseEntity.ok(postRepository.findAll(sort));
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<Post>> getPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Sort sort = Sort.by(Sort.Order.asc("title"), Sort.Order.desc("createdAt"));
        Pageable request = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(postRepository.findAll(request));
    }

}
