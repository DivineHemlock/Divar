package DB;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Objects;

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

    public static ArrayList<Document> findAdByTag (String tag)
    {
        ArrayList<Document> ans = new ArrayList<>();
        DBHandler handler = new DBHandler();
        for (int i = 0 ; i < AD.counter ; i++) // iterate on all documents in ad collection
        {
            String iAsString = String.valueOf(i);
            Document testDoc = new Document("ID",iAsString);
            ArrayList<String> tagList = new ArrayList<>();
            tagList = (ArrayList<String>) handler.getMainDB().getCollection("ads").find(testDoc).cursor().next().get("tags");
            if (tagList.contains(tag))
            {
                ans.add(handler.getMainDB().getCollection("ads").find(testDoc).cursor().next());
            }
        }
        handler.getMongoClient().close();
        return ans;
    }

    public static ArrayList<Document> findAdByCity (String city)
    {
        ArrayList<Document> ans = new ArrayList<>();
        DBHandler handler = new DBHandler();
        for (int i = 0 ; i < AD.counter ; i++) // iterate on all documents in ad collection
        {
            String iAsString = String.valueOf(i);
            Document testDoc = new Document("ID",iAsString);
            if (handler.getMainDB().getCollection("ads").find(testDoc).cursor().next().get("city").equals(city))
            {
                ans.add(handler.getMainDB().getCollection("ads").find(testDoc).cursor().next());
            }
        }
        handler.getMongoClient().close();
        return ans;
    }

    public static ArrayList<Document> findAdByPriceRange (String minPrice , String maxPrice)
    {
        int minPriceAsInt = Integer.parseInt(minPrice);
        int maxPriceAsInt = Integer.parseInt(maxPrice);
        ArrayList<Document> ans = new ArrayList<>();
        DBHandler handler = new DBHandler();
        for (int i = 0 ; i < AD.counter ; i++) // iterate on all documents in ad collection
        {
            String iAsString = String.valueOf(i);
            Document testDoc = new Document("ID" , iAsString);
            int adPrice = Integer.parseInt((String) handler.getMainDB().getCollection("ads").find(testDoc).cursor().next().get("price")) ;
            if (adPrice >= minPriceAsInt && adPrice <= maxPriceAsInt)
            {
                ans.add(handler.getMainDB().getCollection("ads").find(testDoc).cursor().next());
            }
        }
        handler.getMongoClient().close();
        return ans;
    }


    //******************************************************************************************************
    //********************************************** AD METHODS ********************************************
    //******************************************************************************************************

    //******************************************************************************************************
    //********************************************** CHAT METHODS ******************************************
    //******************************************************************************************************

    public static boolean addChat (Chat chat)
    {
        DBHandler handler = new DBHandler();
        Document doc = new Document();
        doc = Document.parse(new Gson().toJson(chat));
        handler.getMainDB().getCollection("chats").insertOne(doc);
        handler.getMongoClient().close();
        return true;
    }

    public static boolean updateChatWithNewMessage (Chat chat , Message message)
    {
        DBHandler handler = new DBHandler();
        handler.getMainDB().getCollection("chats").deleteOne(Objects.requireNonNull(findChatByTwoUsernames(chat.getUserAUsername(), chat.getUserBUsername()))); // delete the old chat from the DB
        chat.addMessage(message); // update the chat with the new message
        addChat(chat); // add the new chat to DB
        handler.getMongoClient().close();
        return true;
    }

    public static Document findChatByTwoUsernames (String username_a , String username_b)
    {
        DBHandler handler = new DBHandler();
        for (int i = 0 ; i < Chat.counter ; i++)
        {
            String iAsString = String.valueOf(i);
            Document testDoc = new Document("ID" ,iAsString);
            Document chat = handler.getMainDB().getCollection("chats").find(testDoc).cursor().next();
            if (chat.get("userAUsername").toString().equals(username_a) && chat.get("userBUsername").toString().equals(username_b))
            {
                Document ans = handler.getMainDB().getCollection("chats").find(testDoc).cursor().next();
                handler.getMongoClient().close();
                return ans;
            }
        }
        return null;
    }

    public static ArrayList<Document> findChatByOneUsername (String username)
    {
        ArrayList<Document> ans = new ArrayList<>();
        DBHandler handler = new DBHandler();
        for (int i = 0 ; i < Chat.counter ; i++)
        {
            String iAsString = String.valueOf(i);
            Document testDoc = new Document("ID" , iAsString);
            Document chat = handler.getMainDB().getCollection("chats").find(testDoc).cursor().next();
            if (chat.get("userAUsername").toString().equals(username) || chat.get("userBUsername").toString().equals(username))
            {
                ans.add(chat);
            }
        }
        handler.getMongoClient().close();
        return ans;
    }
}
