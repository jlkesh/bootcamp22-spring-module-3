package dev.jlkeesh.springadvanced.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {

    private final MongoTemplate mongoTemplate;

    public BookRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Book save(Book book) {
        return mongoTemplate.save(book);
    }

    public List<Book> getAll(String direction) {
        Query query = new Query();
        //Pageable pageable = PageRequest.of(0, 10);
        Sort sort = Sort.by(Sort.Direction.fromString(direction), "author");
        query.with(sort);
        return mongoTemplate.find(query, Book.class);
    }

    public void update(Book book) {
        Query query = new Query(Criteria.where("_id").is(book.getId()));
        Update update = new Update();
        update.set("name", book.getName());
        update.set("author", book.getAuthor());
        Query query2 = new BasicQuery("{_id :  ObjectId('%s')}".formatted(book.getId()));
        BasicUpdate basicUpdate = new BasicUpdate("{$set : {name : '%s', author : '%s'}}".formatted(book.getName(), book.getAuthor()));
        mongoTemplate.updateFirst(query2, basicUpdate, Book.class);
    }

    public Book getById(String id) {
        return mongoTemplate.findOne(new BasicQuery("{_id :  ObjectId('%s')}".formatted(id)), Book.class);
    }
}
