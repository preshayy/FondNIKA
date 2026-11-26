package com.example.fondnika;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChatWork extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);
        RecyclerView rvMessages = findViewById(R.id.rvMessages);
        EditText etMessage = findViewById(R.id.etMessage);
        ImageButton btnSend = findViewById(R.id.btnSend);
        //1. Наполняем чат стартовыми сообщениями, чтобы сразу можно было листать
        List<Message> messageList = new ArrayList<>();
        messageList.add(new Message("Здравствуйте! Я помощник фонда НИКА.", false));
        messageList.add(new Message("Подскажите, как взять собаку из приюта?", true));
        messageList.add(new Message("Вы можете выбрать питомца в каталоге или задать мне любой вопрос!", false));

        ChatAdapter adapter = new ChatAdapter(messageList);
        rvMessages.setLayoutManager(new LinearLayoutManager(this));
        rvMessages.setAdapter(adapter);

        btnSend.setOnClickListener(v -> {
            String text = etMessage.getText().toString().trim();
            if (!text.isEmpty()) {
                adapter.addMessage(new Message(text, true));
                etMessage.setText(""); // очищаем поле ввода

                // Плавно прокручиваем вниз к новому сообщению
                rvMessages.smoothScrollToPosition(messageList.size() - 1);
            }

        });

    }
}
