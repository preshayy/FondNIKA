package com.example.fondnika;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import androidx.activity.OnBackPressedCallback;


public class ChatWork extends AppCompatActivity {

    private ChatAdapter adapter;
    private RecyclerView rvMessages;
    private List<Message> messageList;
    private View menuOverlay;
    private LinearLayout sideMenuPanel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        ImageButton triPoloski = findViewById(R.id.triPoloski);
        menuOverlay = findViewById(R.id.menuOverlay);
        sideMenuPanel = findViewById(R.id.sideMenuPanel);

        View menuProfile = findViewById(R.id.menuProfile);
        View menuOfficialSite = findViewById(R.id.menuOfficialSite);
        View menuPetsCatalog = findViewById(R.id.menuPetsCatalog);

        rvMessages = findViewById(R.id.rvMessages);
        EditText etMessage = findViewById(R.id.etMessage);
        ImageButton btnSend = findViewById(R.id.btnSend);
        Button btnFindDog = findViewById(R.id.btnFindDog);
        Button btnFindCat = findViewById(R.id.btnFindCat);
        Button btnBecomeOwner = findViewById(R.id.btnBecomeOwner);


        if (triPoloski != null) {
            triPoloski.setOnClickListener(v -> openSideMenu());
        }
        if (menuOverlay != null) {
            menuOverlay.setOnClickListener(v -> closeSideMenu());
        }
        if (menuProfile != null) {
            menuProfile.setOnClickListener(v -> {
                closeSideMenu();
                Intent intent = new Intent(ChatWork.this, ProfileActivity.class);
                startActivity(intent);
            });
        }
        if (menuOfficialSite != null) {
            menuOfficialSite.setOnClickListener(v -> {
                closeSideMenu();
                openUrl("https://fond-nika.ru");
            });
        }
        if (menuPetsCatalog != null) {
            menuPetsCatalog.setOnClickListener(v -> {
                closeSideMenu();
                openUrl("https://fond-nika.ru");
            });
        }


        messageList = new ArrayList<>();
        messageList.add(new Message("Здравствуйте! Я помощник фонда НИКА. Подскажу, как выбрать питомца, оформить опеку или помочь приюту.", false));

        if (rvMessages != null) {
            adapter = new ChatAdapter(messageList);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setStackFromEnd(true);
            rvMessages.setLayoutManager(layoutManager);
            rvMessages.setAdapter(adapter);
        }

        if (btnSend != null && etMessage != null) {
            btnSend.setOnClickListener(v -> {
                String text = etMessage.getText().toString().trim();
                if (!text.isEmpty()) {
                    sendUserMessage(text);
                    etMessage.setText("");
                }
            });
        }

        if (btnFindDog != null) {
            btnFindDog.setOnClickListener(v -> sendUserMessage(btnFindDog.getText().toString()));
        }
        if (btnFindCat != null) {
            btnFindCat.setOnClickListener(v -> sendUserMessage(btnFindCat.getText().toString()));
        }
        if (btnBecomeOwner != null) {
            btnBecomeOwner.setOnClickListener(v -> sendUserMessage(btnBecomeOwner.getText().toString()));
        }
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (sideMenuPanel != null && sideMenuPanel.getVisibility() == View.VISIBLE) {
                    closeSideMenu();
                } else {
                    setEnabled(false);
                    getOnBackPressedDispatcher().onBackPressed();
                }
            }
        });

    }

    private void openSideMenu() {
        if (menuOverlay != null) {
            menuOverlay.setVisibility(View.VISIBLE);
        }
        if (sideMenuPanel != null) {
            sideMenuPanel.setVisibility(View.VISIBLE);
            TranslateAnimation slideIn = new TranslateAnimation(-600f, 0f, 0f, 0f);
            slideIn.setDuration(220);
            sideMenuPanel.startAnimation(slideIn);
        }
    }

    private void closeSideMenu() {
        if (menuOverlay != null) {
            menuOverlay.setVisibility(View.GONE);
        }
        if (sideMenuPanel != null) {
            sideMenuPanel.setVisibility(View.GONE);
        }
    }

    private void sendUserMessage(String text) {
        if (adapter == null || rvMessages == null) return;
        adapter.addMessage(new Message(text, true));
        rvMessages.smoothScrollToPosition(messageList.size() - 1);

        String reply = getBotReply(text);
        rvMessages.postDelayed(() -> {
            adapter.addMessage(new Message(reply, false));
            rvMessages.smoothScrollToPosition(messageList.size() - 1);
        }, 400);
    }

    private String getBotReply(String userText) {
        String lower = userText.toLowerCase();
        if (lower.contains("собак")) {
            return "У нас более 30 подопечных! Посмотреть анкеты собак можно в разделе «Наши подопечные ↗» в боковом меню или на сайте fond-nika.ru.";
        } else if (lower.contains("кош") || lower.contains("кот")) {
            return "Котики ждут своих хозяев в каталоге «Наши подопечные ↗». Все животные привиты, стерилизованы и готовы к переезду домой.";
        } else if (lower.contains("опек")) {
            return "Стать опекуном можно дистанционно: вы выбираете питомца на сайте и помогаете оплачивать его корм и уход. Написать кураторам можно на opeka@fond-nika.ru.";
        }
        return "Спасибо за обращение! По срочным вопросам можно позвонить в фонд по номеру +7 (495) 162-85-95 или открыть каталог питомцев в меню слева.";
    }

    private void openUrl(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }
}
