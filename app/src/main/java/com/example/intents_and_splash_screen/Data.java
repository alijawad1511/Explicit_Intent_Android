package com.example.intents_and_splash_screen;

import android.app.Application;

import java.util.ArrayList;

public class Data extends Application {

    static ArrayList<User> users;

    @Override
    public void onCreate() {
        super.onCreate();
        users = new ArrayList<>();
    }

    public static void addUser(User user)
    {
        users.add(user);
    }
}
