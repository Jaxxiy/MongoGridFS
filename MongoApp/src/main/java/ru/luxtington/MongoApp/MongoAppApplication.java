package ru.luxtington.MongoApp;

import com.mongodb.client.MongoClient;
import org.bson.BsonDocument;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.luxtington.MongoApp.models.Toy;

import java.util.List;


@SpringBootApplication
public class MongoAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoAppApplication.class, args);
	}

}
