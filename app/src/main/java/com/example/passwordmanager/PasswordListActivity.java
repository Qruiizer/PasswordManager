package com.example.passwordmanager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PasswordListActivity extends AppCompatActivity {

    private Button addButton;
    private TextView listTextView;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_list);

        addButton = findViewById(R.id.btn_add);
        listTextView = findViewById(R.id.tv_list);
        dbHelper = new DBHelper(this);

        addButton.setOnClickListener(v -> {
            startActivity(new Intent(this, AddPasswordActivity.class));
        });

        showPasswordList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showPasswordList();
    }

    private void showPasswordList() {
        StringBuilder text = new StringBuilder();
        Cursor cursor = dbHelper.getAllPasswords();
        if (cursor.moveToFirst()) {
            int serviceIndex = cursor.getColumnIndex(DBHelper.KEY_SERVICE);
            int loginIndex = cursor.getColumnIndex(DBHelper.KEY_LOGIN);
            int passwordIndex = cursor.getColumnIndex(DBHelper.KEY_PASSWORD);
            int notesIndex = cursor.getColumnIndex(DBHelper.KEY_NOTES);

            do {
                String service = cursor.getString(serviceIndex);
                String login = cursor.getString(loginIndex);
                String password = cursor.getString(passwordIndex);
                String notes = cursor.getString(notesIndex);

                text.append("Service: ").append(service).append("\n")
                        .append("Username: ").append(login).append("\n")
                        .append("Password: ").append(password).append("\n")
                        .append("Notes: ").append(notes).append("\n\n");
            } while (cursor.moveToNext());
        }
        cursor.close();
        listTextView.setText(text.toString());
    }
}
