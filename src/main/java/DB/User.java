package DB;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mongodb.internal.connection.MultiServerCluster;
import java.lang.ref.PhantomReference;
import java.util.ArrayList;

public class User
{
    private String username;
    private String password;
    private String name;
    private String lastName;
    private String phoneNumber;
    private String city;
    private String isPicturePublic;
    private String isNumberPublic;
    private ArrayList<String> bookmarkIDs;
    private String pictureName;

    public User(String username, String password, String name, String lastName, String phoneNumber, String city, String isPicturePublic, String isNumberPublic)
    {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.isPicturePublic = isPicturePublic;
        this.isNumberPublic = isNumberPublic;
        this.bookmarkIDs = new ArrayList<>();
        this.pictureName = username;
    }

    public static User makeUserObjectWithJson (JsonObject json)
    {
        User user = new User(json.get("username").getAsString(),json.get("password").getAsString(),json.get("name").getAsString(),json.get("lastName").getAsString(),json.get("phoneNumber").getAsString(),json.get("city").getAsString(),json.get("isPicturePublic").getAsString(),json.get("isNumberPublic").getAsString());
        // adding the bookmarkIDs list to user
        JsonArray jsonArray = new JsonArray();
        jsonArray = json.getAsJsonArray("bookmarkIDs");
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0 ; i < jsonArray.size() ; i++)
        {
            list.add(String.valueOf(jsonArray.get(i)));
        }
        user.setBookmarkIDs(list);
        return user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIsPicturePublic() {
        return isPicturePublic;
    }

    public void setIsPicturePublic(String isPicturePublic) {
        this.isPicturePublic = isPicturePublic;
    }

    public String getIsNumberPublic() {
        return isNumberPublic;
    }

    public void setIsNumberPublic(String isNumberPublic) {
        this.isNumberPublic = isNumberPublic;
    }

    public ArrayList<String> getBookmarkIDs() {
        return bookmarkIDs;
    }

    public void setBookmarkIDs(ArrayList<String> bookmarkIDs) {
        this.bookmarkIDs = bookmarkIDs;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }
}
