package DB;

import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.Objects;

public class Test
{
    public static void main(String[] args)
    {
        DBHandler handler = new DBHandler();
        handler.getMainDB().getCollection("ads").drop();
        handler.getMainDB().getCollection("chats").drop();
        //System.out.println(User.makeUserObjectWithJson(DBMethods.findUserInDB("a")).getBookmarkIDs());
        String a = "a";
        String b = "b";
        ArrayList<String> list_a = new ArrayList<>();
        ArrayList<String> list_b = new ArrayList<>();
        list_a.add(a);
        list_a.add(b);
        list_b.add(b);
        User user = new User("john" , "as" , "pizza" , "s" ,"23" , "gh" , "1" , " 2");
        User user1 = new User("alireza" , "as" , "pizza" , "s" ,"23" , "gh" , "1" , " 2");
        AD ad = new AD("pizza-a" , "a" , "20000" , "a","hello" , list_a);
        AD ad2 = new AD("pizza-a" , "b" , "2500" , "a","hello" , list_a);
        AD ad3 = new AD("pizza-a" , "c" , "1000" ,"a","hello" , list_a);
        AD ad4 = new AD("pizza-a" , "d" , "900" , "a","hello" , list_a);
        AD ad5 = new AD("pizza-b" , "e" , "2000000000" , "a","hello" , list_b);
        AD ad6 = new AD("pizza-b" , "f" , "2000" , "a","hello" , list_b);
        AD ad7 = new AD("pizza-b" , "g" , "2000" ,"a","hello" , list_b);
        //System.out.println(AD.counter);
        DBMethods.makeNewUser(user);
        DBMethods.makeNewUser(user1);
        /*System.out.println(DBMethods.makeNewAD(ad));
        System.out.println(DBMethods.makeNewAD(ad2));
        System.out.println(DBMethods.makeNewAD(ad3));
        System.out.println(DBMethods.makeNewAD(ad4));
        System.out.println(DBMethods.makeNewAD(ad5));
        System.out.println(DBMethods.makeNewAD(ad6));
        System.out.println(DBMethods.makeNewAD(ad7));*/
        Message message = new Message("salam khobi?" , "a" , "john");
        Message message1 = new Message("salam , mersi" , "john" , "a");
        Message message2 = new Message("yoyoyoyoyo" , "a" , "alireza");
        Chat chat = new Chat("a" , "john");
        Chat chat1 = new Chat("a" , "alireza");
        chat1.addMessage(message2);
        chat.addMessage(message);
        chat.addMessage(message1);
        DBMethods.addChat(chat);
        DBMethods.addChat(chat1);
        System.out.println(DBMethods.findChatByOneUsername("a").get(1).get("messages"));
    }
}