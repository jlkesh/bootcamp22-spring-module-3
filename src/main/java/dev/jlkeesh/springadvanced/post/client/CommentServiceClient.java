package dev.jlkeesh.springadvanced.post.client;

import dev.jlkeesh.springadvanced.post.CommentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@FeignClient(value = "commentServiceClient", url = "${comments.service.baseUrl}")
public interface CommentServiceClient {

    @GetMapping("/{id}/post")
    List<CommentDTO> getCommentListByPostId(@PathVariable Integer id);

    @PostMapping("/list")
    ResponseEntity<Void> saveComments(@RequestBody List<CommentDTO> commentDTOS);

}
