package com.example.passwordmanager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText newUsernameField, newPasswordField, confirmPasswordField;
    private Button registerButton,  backButton;
    private String savedUsername = "";
    private BD bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        bd = new BD(this);

        newUsernameField = findViewById(R.id.et_new_username);
        newPasswordField = findViewById(R.id.et_new_password);
        confirmPasswordField = findViewById(R.id.et_confirm_password);
        registerButton = findViewById(R.id.btn_register);
        backButton = findViewById(R.id.btn_back_to_login);

        backButton.setOnClickListener(v -> {
            finish();
        });

        registerButton.setOnClickListener(v -> {
            String username = newUsernameField.getText().toString();
            String password = newPasswordField.getText().toString();
            String confirm = confirmPasswordField.getText().toString();

            if (username.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirm)) {
                Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show();
                return;
            }

            // Check if username already exists
            if (bd.checkUsernameExist(username)) {
                Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show();
                return;
            }

            long result = bd.addUser(username, password);

            if (result > 0) {
                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
                finish(); // Выход из активности.
            } else {
                Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
            }
        });

        backButton = findViewById(R.id.btn_back_to_login);
        backButton.setOnClickListener(v -> finish());
    }
}