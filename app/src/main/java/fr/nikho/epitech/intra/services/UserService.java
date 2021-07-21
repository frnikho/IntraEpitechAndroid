package fr.nikho.epitech.intra.services;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.stream.Collectors;

import fr.nikho.epitech.intra.data.User;

public class UserService {

    private static final String filename = "user";
    private static User user = null;
    private static String[] friends = null;

    public static boolean loadFriends(Context context) {
        if (friends == null)
            return false;
        SharedPreferences prefs = context.getSharedPreferences("global", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putStringSet("friends", Arrays.stream(friends).collect(Collectors.toSet()));
        return true;
    }

    public static boolean load(Context context) {
        try {
            FileInputStream fis = context.openFileInput(filename);
            ObjectInputStream is = new ObjectInputStream(fis);
            user = (User) is.readObject();
            is.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean save(Context context) {
        if (user == null)
            return false;
        try {
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(user);
            os.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static User getUser(Context context) {
        if (user == null)
            load(context);
        return user;
    }

    public static void reset(Context context) {
        user = null;
        try {
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(null);
            os.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setUser(User newUser) {
        user = newUser;
    }

}
