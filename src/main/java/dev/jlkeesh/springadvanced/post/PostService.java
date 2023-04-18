package dev.jlkeesh.springadvanced.post;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final CommentService commentService;
    private final PostRepository postRepository;

    public PostService(CommentService commentService,
                       PostRepository postRepository) {
        this.commentService = commentService;
        this.postRepository = postRepository;
    }

    public Post create(PostCreateDTO dto) {
        Post post = Post.builder().title(dto.getTitle()).body(dto.getBody()).build();
        postRepository.save(post);
        List<CommentDTO> comments = new ArrayList<>();
        dto.getComments().forEach(message -> comments.add(CommentDTO
                .builder()
                .message(message)
                .postId(post.getId())
                .build()));
        commentService.saveComments(comments);
        return post;
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public PostDTO getPostDetails(Integer id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found: " + id));
        List<CommentDTO> comments = commentService.getCommentsByPostId(post.getId());
        return new PostDTO(
                post.getId(),
                post.getTitle(),
                post.getBody(),
                comments
        );
    }
}

