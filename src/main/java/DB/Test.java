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
        //System.out.println(User.makeUserObjectWithJson(DBMethods.findUserInDB("a")).getBookmarkIDs());
        String a = "a";
        String b = "b";
        ArrayList<String> list_a = new ArrayList<>();
        ArrayList<String> list_b = new ArrayList<>();
        list_a.add(a);
        list_a.add(b);
        list_b.add(b);
        AD ad = new AD("pizza-a" , "a" , "20000" , User.makeUserObjectWithJson(DBMethods.findUserInDB("a")),"hello" , list_a);
        AD ad2 = new AD("pizza-a" , "b" , "2500" , User.makeUserObjectWithJson(DBMethods.findUserInDB("a")),"hello" , list_a);
        AD ad3 = new AD("pizza-a" , "c" , "1000" , User.makeUserObjectWithJson(DBMethods.findUserInDB("a")),"hello" , list_a);
        AD ad4 = new AD("pizza-a" , "d" , "900" , User.makeUserObjectWithJson(DBMethods.findUserInDB("a")),"hello" , list_a);
        AD ad5 = new AD("pizza-b" , "e" , "2000000000" , User.makeUserObjectWithJson(DBMethods.findUserInDB("a")),"hello" , list_b);
        AD ad6 = new AD("pizza-b" , "f" , "2000" , User.makeUserObjectWithJson(DBMethods.findUserInDB("a")),"hello" , list_b);
        AD ad7 = new AD("pizza-b" , "g" , "2000" , User.makeUserObjectWithJson(DBMethods.findUserInDB("a")),"hello" , list_b);
        System.out.println(AD.counter);
        System.out.println(DBMethods.makeNewAD(ad));
        System.out.println(DBMethods.makeNewAD(ad2));
        System.out.println(DBMethods.makeNewAD(ad3));
        System.out.println(DBMethods.makeNewAD(ad4));
        System.out.println(DBMethods.makeNewAD(ad5));
        System.out.println(DBMethods.makeNewAD(ad6));
        System.out.println(DBMethods.makeNewAD(ad7));
        for (int i = 0 ; i < AD.counter ; i++)
        {
            System.out.println(DBMethods.findAdByPriceRange("0" , "20000").get(i).get("price"));
        }
        /*JsonArray jsonArray = new JsonArray();
        jsonArray.add("a");
        jsonArray.add("f");
        System.out.println(jsonArray.get(1));
        */
    }
}
