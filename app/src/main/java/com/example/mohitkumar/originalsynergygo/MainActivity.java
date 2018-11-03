package com.example.mohitkumar.originalsynergygo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mohitkumar.originalsynergygo.Activities.LoginActivity;

public class MainActivity extends AppCompatActivity {

    public static final int EXTERNAL_STORAGE_CODE = 101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
                Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case EXTERNAL_STORAGE_CODE :
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //if (userChoosenTask.equals("STORAGE"))
                    //performTaskOperation();//this method what you need to perform
                }
                break;
        }
    }

    public static class Utility {
        public static boolean checkPermissions(Context context, String... permissions) {
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
                for (String permission : permissions) {
                    if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permission)) {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE},EXTERNAL_STORAGE_CODE);
                        } else {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_CODE);
                        }
                        return false;
                    }
                }
            }
            return true;
        }
    }

}
