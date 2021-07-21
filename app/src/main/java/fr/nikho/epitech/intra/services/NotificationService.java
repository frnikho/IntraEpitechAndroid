package fr.nikho.epitech.intra.services;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;


public class NotificationService {

    private static final String filename = "seen_notifications";

    public List<Integer> loadSeenNotification(Context context) {
        List<Integer> notifs = new ArrayList<>();
        try {
            FileInputStream fis = context.openFileInput(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            String line;
            while ((line = reader.readLine()) != null) {
                notifs.add(Integer.parseInt(line));
            }
            reader.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return notifs;
    }

    public boolean saveSeenNotification(Context context, List<Integer> notifications) {
        try {
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
            notifications.forEach(notification -> {
                try {
                    writer.write(notification.toString());
                    writer.write("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            writer.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean reset(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
            writer.write("");
            writer.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

}
