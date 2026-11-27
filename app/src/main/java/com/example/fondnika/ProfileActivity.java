package com.example.fondnika;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        ImageButton btnBack = findViewById(R.id.btnBack);
        AppCompatButton btnLogout = findViewById(R.id.btnLogout);
        LinearLayout btnOpenWebsite = findViewById(R.id.btnOpenWebsite);
        TextView tvUserEmail = findViewById(R.id.tvUserEmail);

        String passedEmail = getIntent().getStringExtra("USER_EMAIL");
        if (passedEmail != null && !passedEmail.isEmpty()) {
            tvUserEmail.setText(passedEmail);
        }

        // Возврат обратно в чат
        btnBack.setOnClickListener(v -> finish());

        // Переход на сайт фонда НИКА
        btnOpenWebsite.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://fond-nika.ru"));
            startActivity(browserIntent);
        });

        // Выход из аккаунта на экран регистрации
        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }
}
