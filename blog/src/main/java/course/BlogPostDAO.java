package course;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import org.bson.BSON;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class BlogPostDAO {
    MongoCollection<Document> postsCollection;

    public BlogPostDAO(final MongoDatabase blogDatabase) {
        postsCollection = blogDatabase.getCollection("posts");
    }

    // Return a single post corresponding to a permalink
    public Document findByPermalink(String permalink) {

        Document post = null;

        Bson filter = Filters.eq("permalink", permalink);
        post = postsCollection.find(filter).first();

        return post;
    }

    public List<Document> findByDateDescending(int limit) {

        List<Document> posts = null;
        Bson sort = Sorts.descending("date");
        posts = postsCollection.find()
                               .sort(sort)
                               .limit(limit)
                               .into(new ArrayList<Document>());

        return posts;
    }


    public String addPost(String title, String body, List tags, String username) {

        System.out.println("inserting blog entry " + title + " " + body);

        String permalink = title.replaceAll("\\s", "_");
        permalink = permalink.replaceAll("\\W", "");
        permalink = permalink.toLowerCase();
        permalink = permalink+ (new Date()).getTime();

        Document post = new Document();

        post.append("title", title)
                .append("author", username)
                .append("body", body)
                .append("permalink", permalink)
                .append("tags", tags)
                .append("comments", new ArrayList<String>())
                .append("date", new Date());

        postsCollection.insertOne(post);

        return permalink;
    }

    public void addPostComment(final String name, final String email, final String body,
                               final String permalink) {


        Document comment = new Document();
        List<Document> comments;

        Document doc = findByPermalink(permalink);

        comments = (List<Document>) doc.get("comments");

        comment.append("author", name).append("body", body);

        if (email != null && !email.equals("")){
            comment.append("email", email);
        }

        comments.add(comment);

        postsCollection.updateOne(Filters.eq("permalink", permalink), new Document("$set", new Document("comments", comments)));


    }
}
