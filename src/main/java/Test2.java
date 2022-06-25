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
        String s = Objects.requireNonNull(DBMethods.findUserInDB("a", "b")).toString();
        Gson gson = new Gson();
        User user = gson.fromJson(s , User.class);
    }
}
