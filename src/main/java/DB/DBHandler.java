package DB;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

// this class is used for connecting to database and managing it

public class DBHandler
{
    private MongoClient mongoClient;
    private MongoDatabase mainDB;
    private MongoCollection users;
    private MongoCollection ads;

    public DBHandler()
    {
        this.mongoClient = MongoClients.create("mongodb://localhost:27017/local?authSource=admin");
        this.mainDB = mongoClient.getDatabase("mainDB");
        this.users = mainDB.getCollection("users");
        this.ads = mainDB.getCollection("ads");
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public void setMongoClient(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public MongoDatabase getMainDB() {
        return mainDB;
    }

    public void setMainDB(MongoDatabase mainDB) {
        this.mainDB = mainDB;
    }

    public MongoCollection getUsers() {
        return users;
    }

    public void setUsers(MongoCollection users) {
        this.users = users;
    }

    public MongoCollection getAds() {
        return ads;
    }

    public void setAds(MongoCollection ads) {
        this.ads = ads;
    }
}
