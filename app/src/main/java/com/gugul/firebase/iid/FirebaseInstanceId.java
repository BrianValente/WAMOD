package com.gugul.firebase.iid;

/**
 * Created by brianvalente on 8/2/16.
 */
public class FirebaseInstanceId {

    public static FirebaseInstanceId getInstance() {
        return new FirebaseInstanceId();
    }

    public String getToken() {
        return "";
    }
}
