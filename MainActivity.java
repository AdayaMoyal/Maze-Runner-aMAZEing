package com.example.appbyadaya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public void toSignupScreen(View v){
        Intent toSignupIntent = new Intent(this, SignUpActivity.class);
        startActivity(toSignupIntent);
    }

    public void toLoginScreen(View v){
        Intent toLoginIntent = new Intent(this, LoginActivity.class);
        startActivity(toLoginIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyReceiver myReceiver = new MyReceiver();
        int permissionCheck = this.checkSelfPermission(Manifest.permission.READ_PHONE_STATE);
        if(permissionCheck != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
        }
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PHONE_STATE");
        this.registerReceiver(myReceiver, intentFilter);

    }
}