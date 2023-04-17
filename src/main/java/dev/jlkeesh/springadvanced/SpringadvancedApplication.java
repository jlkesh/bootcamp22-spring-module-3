package dev.jlkeesh.springadvanced;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import org.bson.*;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import javax.print.Doc;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Filter;

public class SpringadvancedApplication {

    public static void main(String[] args) {
        try {
            MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017/?directConnection=true");
            MongoDatabase db = mongoClient.getDatabase("mongodb");
            /*db.createCollection("users");
            MongoCollection<Document> users = db.getCollection("users");
            URL url = new URL("https://jsonplaceholder.typicode.com/users");
            ObjectMapper objectMapper = new ObjectMapper();
            List<Map<String, Object>> usersJson = objectMapper.readValue(url, new TypeReference<List<Map<String, Object>>>() {
            });
            List<Document> usersDocument = usersJson
                    .stream()
                    .map(Document::new)
                    .toList();
            users.insertMany(usersDocument);*/
            MongoCollection<Document> users = db.getCollection("users");
            /*Bson filter = Filters.or(
             *//*Filters.eq("address.zipcode", "76495-3109"),
                    Filters.eq("phone", "024-648-3804"),
                    Filters.regex("name",".*an.*"),*//*
                    Filters.where("this.company.name.length > 15")
            );
            FindIterable<Document> documents = users
                    .find(filter)
                    *//*.skip(5)
                    .limit(10)*//*
             *//*.first()*//*;
            documents.forEach(System.out::println);
            *//*System.out.println(document);
            Document company = document.get("company", Document.class);
            System.out.println("company.getString(\"name\") = " + company.getString("name"));*//*

             */
/*
            Bson filter = Filters.eq("_id", new ObjectId("643d1eeebbb9176de2b4b228"));
            //Bson nativeFilter = BsonDocument.parse("{_id : ObjectId('643d1eeebbb9176de2b4b228')}");
            Document nativeFilter = new Document("_id", new ObjectId("643d1eeebbb9176de2b4b228"));
            Document document = users.find(nativeFilter).first();
            System.out.println(document);
            */
/*Bson combine = Updates.combine(
                    Updates.set("username", "Elshod"),
                    Updates.set("name", "Elshodbek"),
                    Updates.set("phone", "+998901234567")
            );
            UpdateOptions updateOptions = new UpdateOptions();
            users.updateOne(nativeFilter, combine, updateOptions);*//*

            Bson query = BsonDocument.parse("{$set : {\"username\" : \"Obidjonbek\", \"phone\" : \"+998944660444\"}}");
            users.updateOne(nativeFilter,query);
*/
            Post post = new Post(null, "Mongo", "MongoDB", new Date());
            new PostDao().save(post);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
