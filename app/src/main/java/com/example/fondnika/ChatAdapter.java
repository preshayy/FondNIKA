package com.example.fondnika;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MessageViewHolder> {

    private final List<Message> messages;

    public ChatAdapter(List<Message> messages) {
        this.messages = messages;
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        LinearLayout container;
        TextView tvText;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.messageContainer);
            tvText = itemView.findViewById(R.id.tvMessageText);
        }
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message message = messages.get(position);
        holder.tvText.setText(message.getText());

        if (message.isUser()) {
            // Ваше сообщение — справа (с персиковым фоном)
            holder.container.setGravity(Gravity.END);
            holder.tvText.setBackgroundResource(R.drawable.bg_chat_input);
        } else {
            // Сообщение помощника — слева (светло-зелёный фон)
            holder.container.setGravity(Gravity.START);
            holder.tvText.setBackgroundResource(R.drawable.bg_bubble_bot);
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void addMessage(Message message) {
        messages.add(message);
        notifyItemInserted(messages.size() - 1);
    }
}
