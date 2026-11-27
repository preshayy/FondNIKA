package com.example.fondnika;

import android.content.Intent;
import android.content.SharedPreferences; // <-- Добавили импорт
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

        TextView tvUserName = findViewById(R.id.tvUserName); // <-- ПРОВЕРЬТЕ ЭТОТ ID В profile.xml!
        TextView tvUserEmail = findViewById(R.id.tvUserEmail);

        // === ВСТАВЬТЕ ЭТОТ БЛОК ДЛЯ ЧТЕНИЯ ИМЕНИ И ПОЧТЫ ===
        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String savedName = prefs.getString("USER_NAME", "Ника");
        String savedEmail = prefs.getString("USER_EMAIL", "volunteer@nika-fond.ru");

        if (tvUserName != null) {
            tvUserName.setText(savedName);
        }
        if (tvUserEmail != null) {
            tvUserEmail.setText(savedEmail);
        }
        // ===================================================

        // Возврат обратно в чат
        btnBack.setOnClickListener(v -> finish());

        // Переход на сайт фонда НИКА
        btnOpenWebsite.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://fond-nika.ru"));
            startActivity(browserIntent);
        });

        // Выход из аккаунта на экран регистрации
        btnLogout.setOnClickListener(v -> {
            // Очищаем сохранённые данные при выходе
            prefs.edit().clear().apply();

            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }
}
