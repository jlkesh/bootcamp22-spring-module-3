package dev.jlkeesh.springadvanced.post;

import dev.jlkeesh.springadvanced.post.client.CommentServiceClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@Service
public class CommentService {

    private final CommentServiceClient commentServiceClient;
    @Value("${comments.service.commentsForPost}")
    private String commentsForPostUrl;

    @Value("${comments.service.saveComments}")
    private String saveCommentsUrl;

    private final RestTemplate restTemplate;

    public CommentService(RestTemplate restTemplate,
                          CommentServiceClient commentServiceClient) {
        this.restTemplate = restTemplate;
        this.commentServiceClient = commentServiceClient;
    }

    public void saveComments(List<CommentDTO> comments) {
        if (!comments.isEmpty()) {
            commentServiceClient.saveComments(comments);
           /* WebClient webClient = WebClient.builder().build();
            webClient.post()
                    .uri(saveCommentsUrl)
                    .body(BodyInserters.fromValue(comments))
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();*/
            /*HttpEntity<List<CommentDTO>> httpEntity = new HttpEntity<>(comments);
            restTemplate.exchange(
                    saveCommentsUrl,
                    HttpMethod.POST,
                    httpEntity,
                    Void.class)*/
        }
    }

    public List<CommentDTO> getCommentsByPostId(Integer postId) {
        return commentServiceClient.getCommentListByPostId(postId);
        /*try {
            WebClient webClient = WebClient.create();
            return webClient.get()
                    .uri(commentsForPostUrl, postId)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<CommentDTO>>() {
                    })
                    .block();
            *//*return restTemplate.exchange(
                            commentsForPostUrl,
                            HttpMethod.GET,
                            HttpEntity.EMPTY,
                            new ParameterizedTypeReference<List<CommentDTO>>() {
                            },
                            postId)
                    .getBody();*//*
        } catch (ResourceAccessException e) {
            e.printStackTrace();
        }
        return List.of();*/
    }
}
