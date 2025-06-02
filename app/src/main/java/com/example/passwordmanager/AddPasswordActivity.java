package com.example.passwordmanager;

import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddPasswordActivity extends AppCompatActivity {

    private EditText serviceField, usernameField, passwordField;
    private Button saveButton;
    private CheckBox showCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_password);

        serviceField = findViewById(R.id.et_service);
        usernameField = findViewById(R.id.et_username);
        passwordField = findViewById(R.id.et_password);
        saveButton = findViewById(R.id.btn_save);
        showCheckbox = findViewById(R.id.cb_show);

        showCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                passwordField.setTransformationMethod(null);
            } else {
                passwordField.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });

        saveButton.setOnClickListener(v -> {
            String service = serviceField.getText().toString();
            String username = usernameField.getText().toString();
            String password = passwordField.getText().toString();

            if (service.isEmpty() || username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "Пароль сохранен", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
