package DB;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    public static JsonObject findUserInDB (String username , String password)
    {
        DBHandler handler = new DBHandler();
        Document doc = new Document("username" , username);
        doc.append("password" , password);
        if (handler.getMainDB().getCollection("users").find(doc).cursor().hasNext()) // if such user exists...
        {
            JsonParser parser = new JsonParser();
            JsonObject ans = (JsonObject) parser.parse(handler.getMainDB().getCollection("users").find(doc).cursor().next().toJson());
            handler.getMongoClient().close();
            return ans;
        }
        else
        {
            handler.getMongoClient().close();
            return null;
        }
    }

    public static ArrayList<Document> findUsersBookmarkedAds (String username)
    {
        ArrayList<Document> ans = new ArrayList<>();
        DBHandler handler = new DBHandler();
        Gson gson = new Gson();
        Document userDoc = new Document("username" , username);
        User user = gson.fromJson(handler.getMainDB().getCollection("users").find(userDoc).cursor().next().toJson() , User.class);
        for (String bookmarkID : user.getBookmarkIDs()) // iterating on all of the users bookmarkIDs
        {
            for (int i = 0 ; i < AD.counter ; i++) // iterating on all ads
            {
                Document adDoc = new Document("ID" , String.valueOf(i));
                AD ad = gson.fromJson(handler.getMainDB().getCollection("ads").find(adDoc).cursor().next().toJson() , AD.class);
                if (bookmarkID.equals(ad.getID())) // if the adID and the bookmarkID are equal...
                {
                    ans.add(handler.getMainDB().getCollection("ads").find(adDoc).cursor().next());
                }
            }
        }
        handler.getMongoClient().close();
        return ans;
    }

    public static ArrayList<Document> findUserAds (String username)
    {
        DBHandler handler = new DBHandler();
        ArrayList<Document> ans = new ArrayList<>();
        for (int i = 0 ; i < AD.counter ; i++)
        {
            Document document = new Document();
            document.append("ID" , String.valueOf(i));
            if (handler.getMainDB().getCollection("ads").find(document).cursor().next().get("username").toString().equals(username))
            {
                ans.add(handler.getMainDB().getCollection("ads").find(document).cursor().next());
            }
        }
        handler.getMongoClient().close();
        return ans;
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
            if (handler.getMainDB().getCollection("ads").find(testDoc).cursor().next().get("tag").equals(tag))
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

    public static ArrayList<Document> findAdByText (String text)
    {
        ArrayList<Document> ans = new ArrayList<>();
        DBHandler handler = new DBHandler();
        for (int i = 0 ; i < AD.counter ; i++) // iterate on all documents in ad collection
        {
            String iAsString = String.valueOf(i);
            Document testDoc = new Document("ID" , iAsString);
            boolean flag = handler.getMainDB().getCollection("ads").find(testDoc).cursor().next().get("info").toString().contains(text);
            boolean flag_1 = handler.getMainDB().getCollection("ads").find(testDoc).cursor().next().get("name").toString().contains(text);
            if (flag || flag_1)
            {
                ans.add(handler.getMainDB().getCollection("ads").find(testDoc).cursor().next());
            }
        }
        handler.getMongoClient().close();
        return ans;
    }


    public static void deleteExpiredAds ()
    {
        DBHandler handler = new DBHandler();
        for (int i = 0 ; i < AD.counter ; i++) // iterate on all documents in ad collection
        {
            String iAsString = String.valueOf(i);
            Document testDoc = new Document("ID" , iAsString);
            testDoc = handler.getMainDB().getCollection("ads").find(testDoc).cursor().next();
            Gson gson = new Gson();
            AD ad = gson.fromJson(testDoc.toJson() , AD.class);
            Calendar calendar = Calendar.getInstance();
            if (calendar.getTime().after(ad.getExpirationDate())) // if the current day is after the expiration date...
            {
                handler.getMainDB().getCollection("ads").deleteOne(testDoc);
            }
        }
        handler.getMongoClient().close();
    }

    public static ArrayList<Document> globalSearch (String city , String tag , String text , String minPrice , String maxPrice)
    {
        DBHandler handler = new DBHandler();
        ArrayList<Document> cities = new ArrayList<>();
        ArrayList<Document> tags = new ArrayList<>();
        ArrayList<Document> texts = new ArrayList<>();
        ArrayList<Document> prices = new ArrayList<>();
        ArrayList<Document> ans = new ArrayList<>();
        if (!city.equals("")) // if city isn't empty , then we are searching based on city
        {
            cities = DBMethods.findAdByCity(city);
        }

        if (!tag.equals("")) // if tag isn't empty , them we are searching based on tag
        {
            tags = DBMethods.findAdByTag(tag);
        }

        if (!text.equals("")) // if text isn't empty , then we are searching based on text
        {
            texts = DBMethods.findAdByText(text);
        }

        if (!minPrice.equals("") && !maxPrice.equals(""))
        {
            prices = DBMethods.findAdByPriceRange(minPrice , maxPrice);
        }

        ans = cities;

        if (!tag.equals(""))
        {
            for (Document doc : ans)
            {
                if (!tags.contains(doc))
                {
                    ans.remove(doc);
                }
            }
        }

        if (!text.equals(""))
        {
            for (Document doc : ans)
            {
                if (!texts.contains(doc))
                {
                    ans.remove(doc);
                }
            }
        }

        if (!minPrice.equals(""))
        {
            for (Document doc : ans)
            {
                if (!prices.contains(doc))
                {
                    ans.remove(doc);
                }
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
