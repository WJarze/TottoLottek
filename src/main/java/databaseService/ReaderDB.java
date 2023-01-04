package databaseService;

import com.mongodb.client.FindIterable;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;


public class ReaderDB {
    FindIterable<Document> iterable;
    List<Document> results = new ArrayList<> ( );

    public List<Document> getResults() {
        return results;
    }

    public void listHitFromDB(ConnectToDatabase database) {
        iterable = database.collection ( ).find ( );
        iterable.into ( results );
    }
    public int size() {
        return results.size ();
    }
}
