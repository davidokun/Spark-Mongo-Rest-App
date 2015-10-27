package com.davidokun.spark.examples;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

/**
 * Created by David Cuellar.
 */
public class RemoveDocumentsHW {

    public static void main(String[] args) {

        MongoClient client = new MongoClient("localhost", 27017);
        MongoDatabase db = client.getDatabase("students");
        MongoCollection<Document> coll = db.getCollection("grades");

        Bson filter = and(eq("type", "homework"));
        Bson sort = and(eq("student_id", 1), eq("score", 1));

        List<Document> docs =  coll.find(filter).sort(sort).into(new ArrayList<Document>());

        for (int i = 0; i < docs.size(); i+=2){
            coll.deleteOne(docs.get(i));
        }
    }


}
