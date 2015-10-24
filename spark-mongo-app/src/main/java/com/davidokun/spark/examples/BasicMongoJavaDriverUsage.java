package com.davidokun.spark.examples;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static java.util.Arrays.asList;

/**
 * Created by David Cuellar.
 */
public class BasicMongoJavaDriverUsage {

    public static void main(String[] args) {

        MongoClient client = new MongoClient("localhost", 27017);
        MongoDatabase db = client.getDatabase("spark_mongo");
        MongoCollection<Document> coll = db.getCollection("movies");

        insert(coll);


    }

    /**
     * Insert functionality
     *
     * @param coll instance of MongoCollection
     */
    public static void insert(MongoCollection<Document> coll) {

        coll.drop();

        Document doc = new Document("name", "Back to the future")
                .append("year", 1985)
                .append("director", "Robert Zemekis")
                .append("rating", 5);

        coll.insertOne(doc);

        Document doc2 = new Document("name", "Terminator 2")
                .append("year", 1991)
                .append("director", "James Cameron")
                .append("rating", 5);

        Document doc3 = new Document("name", "Interestellar")
                .append("year", 2014)
                .append("director", "Christopher Nolan")
                .append("rating", 5);

        coll.insertMany(asList(doc2, doc3));
    }
}
