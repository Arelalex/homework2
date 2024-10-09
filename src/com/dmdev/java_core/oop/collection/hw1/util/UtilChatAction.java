package com.dmdev.java_core.oop.collection.hw1.util;

import com.dmdev.java_core.oop.collection.hw1.chat.Chat;

import java.util.Iterator;
import java.util.List;

public final class UtilChatAction {

    private UtilChatAction() {
    }

    public static void deleteChat(List<Chat> chatList) {
        for (Iterator<Chat> iterator = chatList.iterator(); iterator.hasNext(); ) {
            Chat next = iterator.next();
            if (next.getNumberOfUsers() < 1000) {
                iterator.remove();
            }
        }
    }
}
