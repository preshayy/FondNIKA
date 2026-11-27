package com.example.fondnika;

import android.content.Intent;
import android.content.SharedPreferences; // <-- Добавьте этот импорт
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView ivLogo = findViewById(R.id.ivLogo);
        EditText etLogin = findViewById(R.id.etLogin);
        EditText etEmail = findViewById(R.id.email);
        EditText etPassword = findViewById(R.id.etPassword);
        Button btRegistration = findViewById(R.id.btRegistration);

        Picasso.get()
                .load("https://pages.dev")
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(ivLogo);

        btRegistration.setOnClickListener(v -> {
            String name = etLogin.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (name.isEmpty()) {
                etLogin.setError("Введите имя");
                etLogin.requestFocus();
                return;
            }
            if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.setError("Введите корректный email");
                etEmail.requestFocus();
                return;
            }
            if (password.length() < 6) {
                etPassword.setError("Пароль должен быть не менее 6 символов");
                etPassword.requestFocus();
                return;
            }

            // === ВСТАВЬТЕ ЭТОТ БЛОК СЮДА ===
            SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            prefs.edit()
                    .putString("USER_NAME", name)
                    .putString("USER_EMAIL", email)
                    .apply();
            // ===============================

            Intent intent = new Intent(MainActivity.this, ChatWork.class);
            intent.putExtra("USER_NAME", name);
            startActivity(intent);
        });
    }
}
