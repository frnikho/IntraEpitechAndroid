package fr.nikho.epitech.intra.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ProxyActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent == null)
            return;
        Uri uri = intent.getData();
        Toast.makeText(this, "" + uri.toString(), Toast.LENGTH_LONG).show();
    }
}
