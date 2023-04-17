package dev.jlkeesh.springadvanced.post;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {

    //@Query("{post_title : {$regex  : '.*?0$'}}")
    // List<Post> findAllByTitleWithCriteria( String title);
    List<Post> findAllByTitleRegex(String regex);
    //List<Post> findAllByTitleStartingWith(String title);
}
