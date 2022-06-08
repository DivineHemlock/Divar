package DB;

import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.Objects;

public class Test
{
    public static void main(String[] args)
    {
        //System.out.println(User.makeUserObjectWithJson(DBMethods.findUserInDB("a")).getBookmarkIDs());
        String a = "a";
        String b = "b";
        ArrayList<String> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        AD ad = new AD("pizza" , "teh" , "2000" , User.makeUserObjectWithJson(DBMethods.findUserInDB("a")),"hello" , list);
        AD ad2 = new AD("pizza" , "teh" , "2000" , User.makeUserObjectWithJson(DBMethods.findUserInDB("a")),"hello" , list);
        System.out.println(DBMethods.makeNewAD(ad));
        System.out.println(DBMethods.makeNewAD(ad2));
        /*JsonArray jsonArray = new JsonArray();
        jsonArray.add("a");
        jsonArray.add("f");
        System.out.println(jsonArray.get(1));
        */
    }
}
