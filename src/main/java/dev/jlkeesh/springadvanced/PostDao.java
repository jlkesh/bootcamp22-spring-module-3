package dev.jlkeesh.springadvanced;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;

public class PostDao {
    private final MongoClient mongoClient = MongoClients.create("mongodb://domlo:123@localhost:27017");
    private final MongoDatabase mongoDatabase = mongoClient.getDatabase("mongodb");
    private final MongoCollection<Document> posts = mongoDatabase.getCollection("posts");
    private final ObjectMapper objectMapper = new ObjectMapper();


    public Post save(Post post) throws Exception {
        Document document = Document.parse(objectMapper.writeValueAsString(post));
        InsertOneResult insertOneResult = posts.insertOne(document);
        if (insertOneResult.wasAcknowledged()) {
            post.setId(insertOneResult.getInsertedId().asObjectId().getValue());
            return post;
        }
        throw new RuntimeException("Post could not be saved");
    }



}
