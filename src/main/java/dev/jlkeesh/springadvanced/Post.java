package dev.jlkeesh.springadvanced;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class Post {
    private ObjectId id;
    private String title;
    private String body;
    private Date createdAt;
}
