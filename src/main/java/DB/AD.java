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
    private String username;
    private Date submitDate;
    private Date expirationDate;
    private String info;
    private String ID;
    private String tag;

    public AD(String name, String city, String price, String username,String info, String tag)
    {
        this.name = name;
        this.city = city;
        this.price = price;
        this.username = username;
        this.submitDate = calendar.getTime();
        calendar.add(Calendar.DATE , 10);
        this.expirationDate = calendar.getTime();
        calendar.add(Calendar.DATE , -10);
        this.info = info;
        this.ID = String.valueOf(counter);
        this.pictureName = ID;
        this.tag = tag;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
