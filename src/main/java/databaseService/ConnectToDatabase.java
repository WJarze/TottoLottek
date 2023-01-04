package databaseService;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class ConnectToDatabase {

    private final String nameDatabase;
    private final String nameCollection;

    public ConnectToDatabase(String nameDatabase , String nameCollection) {
        this.nameDatabase = nameDatabase;
        this.nameCollection = nameCollection;
    }

    private MongoClient connected() {
        return MongoClients.create ();
    }

    protected MongoDatabase access() {
        return connected ( ).getDatabase ( nameDatabase );
    }

    protected MongoCollection<Document> collection() {
        return access ( ).getCollection ( nameCollection );
    }

    public void clear() {
        access ( ).drop ( );
    }

    public void addOne(Document document) {
        collection ( ).insertOne ( document );
    }

    public void exit() {
        connected ( ).close ( );
    }
}
