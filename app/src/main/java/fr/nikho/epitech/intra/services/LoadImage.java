package fr.nikho.epitech.intra.services;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class LoadImage extends AsyncTask<String, Void, Bitmap> {

    ImageView imageView;

    public LoadImage (ImageView view) {
        this.imageView = view;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        String urlLink = strings[0];
        Bitmap bitmap = null;
        try {
            InputStream is = new URL(urlLink).openStream();
            bitmap = BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        this.imageView.setImageBitmap(bitmap);
    }
}
