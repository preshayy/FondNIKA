package com.example.fondnika;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.net.CookieHandler;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView ivLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivLogo = findViewById(R.id.ivLogo);
        Picasso.get().load("https://nika-design.pages.dev/pomoch/assets/logo-nika.png");

//        // Укажите реальные ID из вашего chat.xml
//        RecyclerView rvMessages = findViewById(R.id.rvMessages);
//        EditText etMessage = findViewById(R.id.etMessage);
//        ImageButton btnSend = findViewById(R.id.btnSend);

        // 1. Наполняем чат стартовыми сообщениями, чтобы сразу можно было листать
//        List<Message> messageList = new ArrayList<>();
//        messageList.add(new Message("Здравствуйте! Я помощник фонда НИКА.", false));
//        messageList.add(new Message("Подскажите, как взять собаку из приюта?", true));
//        messageList.add(new Message("Вы можете выбрать питомца в каталоге или задать мне любой вопрос!", false));

        // 2. Настраиваем список
//        ChatAdapter adapter = new ChatAdapter(messageList);
//        rvMessages.setLayoutManager(new LinearLayoutManager(this));
//        rvMessages.setAdapter(adapter);

//        // 3. Обрабатываем отправку нового сообщения
//        btnSend.setOnClickListener(v -> {
//            String text = etMessage.getText().toString().trim();
//            if (!text.isEmpty()) {
//                adapter.addMessage(new Message(text, true));
//                etMessage.setText(""); // очищаем поле ввода
//
//                // Плавно прокручиваем вниз к новому сообщению
//                rvMessages.smoothScrollToPosition(messageList.size() - 1);
//            }
//        });
    }
}


