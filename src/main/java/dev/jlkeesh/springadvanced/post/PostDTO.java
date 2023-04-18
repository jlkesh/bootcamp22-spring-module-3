package dev.jlkeesh.springadvanced.post;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link Post} entity
 */
public record PostDTO(Integer id,
                      String title,
                      String body,
                      List<CommentDTO> comments
                      ) implements Serializable {
}