package com.whatsapp;

import android.app.Application;

import com.wamod.Chat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brianvalente on 9/21/15.
 */
public class App extends Application {
    public static List<Chat> openedChats = new ArrayList<Chat>();
}
