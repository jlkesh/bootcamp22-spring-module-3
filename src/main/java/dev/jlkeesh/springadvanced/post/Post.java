package dev.jlkeesh.springadvanced.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document("posts")
public class Post {

    @Id
    @JsonIgnore(value = true)
    private String id;

    @Field("post_title")
    /*@Indexed(unique = true,
            name = "post_title_unique_index",
            sparse = true,
            direction = IndexDirection.DESCENDING
    )*/
    private String title;

    private String body;

    @CreatedDate
    private Date createdAt;

    @CreatedBy
    private Long createdBy;

}
