import DB.AD;
import DB.DBHandler;
import DB.DBMethods;
import DB.User;
import com.google.gson.Gson;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class Test2
{
    public static void main(String[] args)
    {
        DBHandler handler = new DBHandler();
        handler.getMainDB().getCollection("ads").drop();
        Calendar calendar = Calendar.getInstance();
        ArrayList<String> list = new ArrayList<>();
        String a = "A";
        list.add(a);
        AD ad = new AD("test" , "testA" , "testB" , "a" , "aaaaaaaaaaa" ,list );
        calendar.add(Calendar.MONTH, -4);
        ad.setExpirationDate(calendar.getTime());
        DBMethods.makeNewAD(ad);
        DBMethods.deleteExpiredAds();

    }
}
