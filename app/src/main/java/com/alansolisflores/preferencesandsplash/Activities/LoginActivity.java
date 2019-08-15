package com.alansolisflores.preferencesandsplash.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.alansolisflores.preferencesandsplash.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Switch rememberSwitch;
    private Button loginButton;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.sharedPreferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);

        bindUI();

        checkPreferences();
    }

    private void checkPreferences(){
        if(this.sharedPreferences.contains("email") &&
                this.sharedPreferences.contains("password")){
            String email = this.sharedPreferences.getString("email","");
            String password = this.sharedPreferences.getString("password","");

            this.emailEditText.setText(email);
            this.passwordEditText.setText(password);
            this.rememberSwitch.setChecked(true);
        }
    }

    private void bindUI(){
        this.emailEditText = findViewById(R.id.emailEditText);
        this.passwordEditText = findViewById(R.id.passwordEditText);
        this.rememberSwitch = findViewById(R.id.rememberSwitch);
        this.loginButton = findViewById(R.id.loginButton);

        this.loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.validForm();
    }

    private void validForm(){
        String email = this.emailEditText.getText().toString().trim();
        String password = this.passwordEditText.getText().toString().trim();

        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this,"Complete form!",Toast.LENGTH_SHORT).show();
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this,"Email incorrect format",Toast.LENGTH_SHORT).show();
        }else {
            this.login(email,password);
        }
    }

    private void saveOnPreferences(String email, String password){
        if(this.rememberSwitch.isChecked() &&
            !this.sharedPreferences.contains("email") &&
                !this.sharedPreferences.contains("password")){
            SharedPreferences.Editor editor = this.sharedPreferences.edit();
            editor.putString("email",email);
            editor.putString("password",password);
            editor.apply();
        }

        if(!this.rememberSwitch.isChecked() &&
                this.sharedPreferences.contains("email") &&
                this.sharedPreferences.contains("password")){
            this.sharedPreferences.edit().clear().apply();
        }
    }

    private void login(String email, String password){
        this.saveOnPreferences(email,password);

        this.goToMain();
    }

    private void goToMain(){
        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
