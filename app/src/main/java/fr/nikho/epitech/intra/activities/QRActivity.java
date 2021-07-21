package fr.nikho.epitech.intra.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import fr.nikho.epitech.intra.R;
import fr.nikho.epitech.intra.data.Dashboard;
import fr.nikho.epitech.intra.fragments.HomeFragment;

public class QRActivity extends AppCompatActivity implements DecodeCallback {

    private HashMap<String, Class> activityCodes = new HashMap<>();

    private static final int PERMISSION_REQUEST_CODE = 200;

    private CodeScanner codeScanner;
    private CodeScannerView codeScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qractivity);
        if (!checkCameraPermission()) {
            requestPermission();
            return;
        }
        bindCameraView();
    }

    private void loadActivityCode() {
        activityCodes.put("dashboard", Dashboard.class);
        activityCodes.put("user", HomeFragment.class);
    }

    private void bindCameraView() {
        codeScannerView = findViewById(R.id.qrcamera_view);
        codeScanner = new CodeScanner(this, codeScannerView);
        codeScanner.setDecodeCallback(this);
        codeScannerView.setOnClickListener(v -> codeScanner.startPreview());
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CODE);
    }

    private boolean checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != 200)
            return;
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            bindCameraView();
        } else {
            Toast.makeText(this, "DENIED", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (codeScanner != null)
            codeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        if (codeScannerView != null)
            codeScanner.releaseResources();
        super.onPause();
    }

    @Override
    public void onDecoded(@NonNull @NotNull Result result) {
        String content = result.getText();
        String requiredPrefix = "intra://";

        if (content.startsWith(requiredPrefix)) {
            Log.e("decoded", "good url = " + content);
            content = content.replaceAll(requiredPrefix, "");
        } else {
            Log.e("decoded: ", "wrong url = " + content);
            finish();
        }

        if (content.startsWith("dashboard")) {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        } else if (content.startsWith("user")) {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            intent.putExtra("viewpager", "user");
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        } else if (content.startsWith("module")) {
            String[] data = content.split("/");
            String year = data[1];
            String moduleCode = data[2];
            String instanceCode = data[3];
            if (data.length == 4) {
                Intent intent = new Intent(getApplicationContext(), ModuleActivity.class);
                intent.putExtra("data", new String[]{year, moduleCode, instanceCode});
                startActivity(intent);
            } else if (data.length == 5) {
                String activity = data[4];
            }
        }

    }
}