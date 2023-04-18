package dev.jlkeesh.springadvanced.post;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostRepository postRepository;
    private final RestTemplate restTemplate;

    public PostController(PostRepository postRepository, RestTemplate restTemplate) {
        this.postRepository = postRepository;
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAll() {
        return ResponseEntity.ok(postRepository.findAll());
    }


    @PostMapping
    public ResponseEntity<Post> create(@RequestBody PostCreateDTO dto) {
        Post post = Post.builder().title(dto.getTitle()).body(dto.getBody()).build();
        postRepository.save(post);
        List<CommentDTO> comments  = new ArrayList<>();
         dto.getComments().forEach(message->{
             comments.add(CommentDTO.builder().message(message).postId(post.getId()).build());
         });

        if (!comments.isEmpty()) {
            String url = "http://localhost:8080/api/comments/list";
            HttpEntity<List<CommentDTO>> httpEntity = new HttpEntity<>(comments);
            ResponseEntity<Void> responseEntity =
                    restTemplate.exchange(url,
                            HttpMethod.POST,
                            httpEntity,
                            Void.class,
                            post.getId());
        }

        return ResponseEntity.status(201).body(post);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostDTO(@PathVariable Integer id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found: " + id));
        String url = "http://localhost:8080/api/comments/{0}/post";
        ResponseEntity<List<CommentDTO>> responseEntity = restTemplate.exchange(url,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<>() {
                }, post.getId());

        List<CommentDTO> comments = responseEntity.getBody();

        return ResponseEntity.ok(new PostDTO(
                post.getId(),
                post.getTitle(),
                post.getBody(),
                comments
        ));
    }

}
