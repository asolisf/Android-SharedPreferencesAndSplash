package com.alansolisflores.preferencesandsplash.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.alansolisflores.preferencesandsplash.R;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private TextView messageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.messageTextView = findViewById(R.id.messageTextView);

        this.sharedPreferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);

        checkPreferences();
    }

    private void checkPreferences(){
        if(this.sharedPreferences.contains("email")){
            String email = this.sharedPreferences.getString("email","");
            this.messageTextView.setText("Hello "+email+"!");
        }else {
            this.messageTextView.setText("Hello!");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.items_menu,menu);

        return true;
    }

    private void deletePreferences(){
        this.sharedPreferences.edit().clear().apply();
    }

    private void logout(){
        Intent intent = new Intent(this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.deleteItem:
                deletePreferences();
                return true;
            case R.id.logoutItem:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
