package DB;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AD
{
    private Calendar calendar = Calendar.getInstance();
    public static int counter = 0;
    private String name;
    private String pictureName;
    private String city;
    private String price;
    private User user;
    private Date submitDate;
    private Date expirationDate;
    private String info;
    private String ID;
    private ArrayList<String> tags = new ArrayList<>();

    public AD(String name, String city, String price, User user,String info, ArrayList<String> tags)
    {
        this.name = name;
        this.city = city;
        this.price = price;
        this.user = user;
        this.submitDate = calendar.getTime();
        calendar.add(Calendar.DATE , 10);
        this.expirationDate = calendar.getTime();
        calendar.add(Calendar.DATE , -10);
        this.info = info;
        this.ID = String.valueOf(counter);
        this.pictureName = ID;
        this.tags = tags;
        AD.counter += 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }
}
