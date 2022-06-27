package com.example.divar3;

import DB.Chat;

public class ChatHolder
{
    private static Chat chat;

    public static Chat getChat() {
        return chat;
    }

    public static void setChat(Chat chat) {
        ChatHolder.chat = chat;
    }
}
