package DB;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.ArrayList;

public class DBMethods
{
    //******************************************************************************************************
    //********************************************** USER METHODS ******************************************
    //******************************************************************************************************

    public static boolean makeNewUser (User user)
    {
        DBHandler handler = new DBHandler();
        // search to see if username is not taken
        boolean isUnique = false;
        Document testDoc = new Document("username" , user.getUsername());
        if(!handler.getMainDB().getCollection("users").find(testDoc).cursor().hasNext())
        {
            isUnique = true;
        }
        if (isUnique) // if username is unique...
        {
            Document doc = new Document();
            doc = Document.parse(new Gson().toJson(user));
            handler.getMainDB().getCollection("users").insertOne(doc);
            handler.getMongoClient().close();
            return true;
        }
        else
        {
            handler.getMongoClient().close();
            return false;
        }
    }

    public static boolean deleteUser (String username)
    {
        DBHandler handler = new DBHandler();
        Document doc = new Document("username" , username);
        if (handler.getMainDB().getCollection("users").find(doc).cursor().hasNext()) // if such user exists
        {
            handler.getMainDB().getCollection("users").deleteOne(doc);
            handler.getMongoClient().close();
            return true;
        }
        else
        {
            handler.getMongoClient().close();
            return false;
        }
    }

    public static boolean editUser (String username , String key , String newValue)
    {
        DBHandler handler = new DBHandler();
        // making sure we are not making a duplicate username
        if (key.equals("username")) // if we are changing the username...
        {
            Document testDoc = new Document("username" , newValue);
            if(handler.getMainDB().getCollection("users").find(testDoc).cursor().hasNext()) // if a user with this username exists...
            {
                // do nothing and exit
                handler.getMongoClient().close();
                return false;
            }
        }
        // if we are not changing a username or if the new username is not repeated...
        Document oldDoc = new Document("username" , username);
        if (handler.getMainDB().getCollection("users").find(oldDoc).cursor().hasNext()) // if such user exists...
        {
            Document newDoc = oldDoc;
            newDoc.put(key,newValue);
            handler.getMainDB().getCollection("users").updateOne(Filters.eq("username" , username) , Updates.set(key , newValue));
            handler.getMongoClient().close();
            return true;
        }
        else
        {
            handler.getMongoClient().close();
            return false;
        }
    }

    public static JsonObject findUserInDB (String username)
    {
        DBHandler handler = new DBHandler();
        Document doc = new Document("username" , username);
        if (handler.getMainDB().getCollection("users").find(doc).cursor().hasNext()) // if such user exists...
        {
            String ans = handler.getMainDB().getCollection("users").find(doc).cursor().next().toJson();
            JsonParser parser = new JsonParser();
            JsonObject json = (JsonObject) parser.parse(ans);
            handler.getMongoClient().close();
            return json;
        }
        else
        {
            handler.getMongoClient().close();
            return null;
        }
    }
    //******************************************************************************************************
    //********************************************** USER METHODS ******************************************
    //******************************************************************************************************

    //******************************************************************************************************
    //********************************************** AD METHODS ********************************************
    //******************************************************************************************************

    public static boolean makeNewAD(AD ad)
    {
        DBHandler handler = new DBHandler();
        // search to see if the ad id is unique
        boolean isUnique = false;
        Document testDoc = new Document("ID", ad.getID());
        if (!handler.getMainDB().getCollection("ads").find(testDoc).cursor().hasNext())
        {
            isUnique = true;
        }
        if (isUnique) // if ad is unique..
        {
            Document doc = new Document();
            doc = Document.parse(new Gson().toJson(ad));
            handler.getMainDB().getCollection("ads").insertOne(doc);
            handler.getMongoClient().close();
            return true;
        }
        else
        {
            handler.getMongoClient().close();
            return false;
        }
    }

    public static boolean deleteAd (String ID)
    {
        DBHandler handler = new DBHandler();
        Document doc = new Document("ID", ID);
        if (handler.getMainDB().getCollection("ads").find(doc).cursor().hasNext()) // if such ad exists...
        {
            handler.getMainDB().getCollection("ads").deleteOne(doc);
            handler.getMongoClient().close();
            return true;
        }
        else
        {
            handler.getMongoClient().close();
            return false;
        }
    }

    public static boolean editAd (String ID , String key , String newValue)
    {
        DBHandler handler = new DBHandler();
        // making sure we are not making a duplicate ID
        if (key.equals("ID")) // if we are changing the ID...
        {
            Document testDoc = new Document("ID" , newValue);
            if (handler.getMainDB().getCollection("ads").find(testDoc).cursor().hasNext()) // if an ad with this id exists...
            {
                // od nothing and exit
                handler.getMongoClient().close();
                return false;
            }
        }
        // if we are not changing an id or if the id is not being repeated...
        Document oldDoc = new Document("ID" , ID);
        if (handler.getMainDB().getCollection("ads").find(oldDoc).cursor().hasNext()) // if such an ad exists...
        {
            Document newDoc = oldDoc;
            newDoc.put(key,newValue);
            handler.getMainDB().getCollection("ads").updateOne(Filters.eq("ID" , ID) , Updates.set(key,newValue));
            handler.getMongoClient().close();
            return true;
        }
        else
        {
            handler.getMongoClient().close();
            return false;
        }
    }

    /*public static ArrayList<JsonObject> findAdByTag (String tag)
    {
        DBHandler handler = new DBHandler();
        Document doc = new Document("tag" , tag);

    }*/





}
