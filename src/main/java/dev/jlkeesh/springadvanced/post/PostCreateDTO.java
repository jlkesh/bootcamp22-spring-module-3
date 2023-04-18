package dev.jlkeesh.springadvanced.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link Post} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateDTO implements Serializable {
    private String title;
    private String body;
    private List<String> comments;
}