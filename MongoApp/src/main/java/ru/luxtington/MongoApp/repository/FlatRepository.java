package ru.luxtington.MongoApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.luxtington.MongoApp.models.Toy;

public interface FlatRepository extends MongoRepository<Toy, ObjectId> {

}
