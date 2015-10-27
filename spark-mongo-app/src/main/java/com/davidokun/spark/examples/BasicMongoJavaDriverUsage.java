package com.davidokun.spark.examples;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.tools.DocumentationTool;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static java.util.Arrays.asList;

/**
 * Created by David Cuellar.
 */
public class BasicMongoJavaDriverUsage {

    public static void main(String[] args) {

        MongoClient client = new MongoClient("localhost", 27017);
        MongoDatabase db = client.getDatabase("spark_mongo");
        MongoCollection<Document> coll = db.getCollection("movies");

        //insert(coll);
        //select(coll);
        selectWithFilters(coll);


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
                .append("rating", 1);

        coll.insertOne(doc);

        Document doc2 = new Document("name", "Terminator 2")
                .append("year", 1991)
                .append("director", "James Cameron")
                .append("rating", 5);

        Document doc3 = new Document("name", "Interestellar")
                .append("year", 2014)
                .append("director", "Christopher Nolan")
                .append("rating", 3);

        Document doc4 = new Document("name", "Batman Begins")
                .append("year", 2009)
                .append("director", "Christopher Nolan")
                .append("rating", 4);

        Document doc5 = new Document("name", "Star Wars : A New Hope")
                .append("year", 1977)
                .append("director", "George Lucas")
                .append("rating", 5);

        coll.insertMany(asList(doc2, doc3, doc4, doc5));
    }

    /**
     * Select functionality
     *
     * @param coll instance of MongoCollection
     */
    public static void select(MongoCollection<Document> coll){

        Document doc = null;

        System.out.println("======== Find first");
        // Find first document
        doc = coll.find().first();
        System.out.println(doc);

        System.out.println("======== Find all");
        //Find all documents
        List<Document> all =  coll.find().into(new ArrayList<Document>());
        for (Document docs : all){
            System.out.println(docs);
        }

        System.out.println("======== Find with cursor");
        //Fins all with cursor
        try(MongoCursor<Document> cursor = coll.find().iterator()){
            while (cursor.hasNext()){
                System.out.println(cursor.next());
            }
        }

        System.out.println("======== Count");
        Long count = coll.count();
        System.out.println(count);

    }

    /**
     * Select with filters
     */
    public static void selectWithFilters(MongoCollection<Document> coll){

        Bson filter = new Document("year", 1991).append("name", "Terminator 2");
        Document doc = coll.find(filter).first();
        System.out.println(doc);

        System.out.println("==========================================");

        filter = new Document("year", new Document("$gt", 1990)).append("rating", new Document("$gt", 3));
        List<Document> docs = coll.find(filter).into(new ArrayList<Document>());
        for (Document d : docs){
            System.out.println(d);
        }

        System.out.println("========= Usign Filters class==========");

        filter = and(eq("year", 1991), eq("name", "Terminator 2"));
        doc = coll.find(filter).first();
        System.out.println(doc);

        System.out.println("==========================================");

        filter = and(gt("year", 1990), gt("rating", 3));
        docs = coll.find(filter).into(new ArrayList<Document>());
        for (Document d : docs){
            System.out.println(d);
        }

    }
}
