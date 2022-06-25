package com.example.divar3;

import DB.User;

public class UserHolder {
    private static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User savedUser) {
        user = savedUser;
    }
}
