package com.alansolisflores.preferencesandsplash.Splash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alansolisflores.preferencesandsplash.Activities.LoginActivity;
import com.alansolisflores.preferencesandsplash.Activities.MainActivity;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkPreferences();
    }

    private void checkPreferences(){
        this.sharedPreferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        if(this.sharedPreferences.contains("email") &&
            this.sharedPreferences.contains("password")){
            this.goToActivity(MainActivity.class);
        }else {
            this.goToActivity(LoginActivity.class);
        }
    }

    private void goToActivity(Class activity){
        Intent intent = new Intent(this,activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
