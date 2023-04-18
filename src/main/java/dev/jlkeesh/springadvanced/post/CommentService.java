package dev.jlkeesh.springadvanced.post;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CommentService {

    @Value("${comments.service.commentsForPost}")
    private String commentsForPostUrl;

    @Value("${comments.service.saveComments}")
    private String saveCommentsUrl;

    private final RestTemplate restTemplate;

    public CommentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void saveComments(List<CommentDTO> comments) {
        if (!comments.isEmpty()) {
            HttpEntity<List<CommentDTO>> httpEntity = new HttpEntity<>(comments);
            restTemplate.exchange(
                    saveCommentsUrl,
                    HttpMethod.POST,
                    httpEntity,
                    Void.class);
        }
    }

    public List<CommentDTO> getCommentsByPostId(Integer postId) {
        try {
            return restTemplate.exchange(
                            commentsForPostUrl,
                            HttpMethod.GET,
                            HttpEntity.EMPTY,
                            new ParameterizedTypeReference<List<CommentDTO>>() {
                            },
                            postId)
                    .getBody();
        } catch (ResourceAccessException e) {
            e.printStackTrace();
        }
        return List.of();
    }
}
