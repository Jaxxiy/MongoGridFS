package ru.luxtington.MongoApp.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "toys_collection")
public class Toy {

    @Id
    @Field("id")
    private ObjectId id;

    @Field("title")
    private String title;

    @Override
    public String toString() {
        return "Toy{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
