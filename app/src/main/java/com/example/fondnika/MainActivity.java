package com.example.fondnika;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fondnika.ChatWork;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Загружаем логотип сразу при открытии экрана регистрации
        ImageView ivLogo = findViewById(R.id.ivLogo);
        Picasso.get()
                .load("https://nika-design.pages.dev/pomoch/assets/logo-nika.png")
                .into(ivLogo);

        // Переход в чат по нажатию на кнопку
        Button btRegistration = findViewById(R.id.btRegistration);
        btRegistration.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ChatWork.class);
            startActivity(intent);
        });
    }
}




