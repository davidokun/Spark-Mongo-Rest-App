package com.davidokun.spark.examples;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
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

        //removeHomeworkGrade1();
        removeHomeworkGrade2();
    }


    public static void removeHomeworkGrade1(){

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

    public static void removeHomeworkGrade2(){

        MongoClient client = new MongoClient("localhost", 27017);
        MongoDatabase db = client.getDatabase("school");
        MongoCollection<Document> collection = db.getCollection("students");

        Bson sort = and(eq("_id", 1), eq("scores.score", 1));

        List<Document> docs =  collection.find()
                                    .sort(sort)
                                    .into(new ArrayList<>());

        docs.stream().forEach(doc -> {

            List<Document> scores = (List<Document>) doc.get("scores");

            Document minScore = scores.stream()
                                  .filter(x -> x.get("type").equals("homework"))
                                  .min((p1, p2) -> Double.compare((Double) p1.get("score"), (Double) p2.get("score")))
                                  .get();

            scores.remove(minScore);

            collection.updateOne(Filters.eq("_id", doc.get("_id")), new Document("$set", new Document("scores", scores)));

        });
    }

}

