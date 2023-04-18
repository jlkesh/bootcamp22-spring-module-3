package dev.jlkeesh.springadvanced.post;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public  class CommentDTO {
    private  Integer id;
    private  String message;
    private  Integer postId;
}
