package com.dmdev.java_core.oop.collection.hw2.util;

import com.dmdev.java_core.oop.collection.hw2.chat.Chat;
import com.dmdev.java_core.oop.collection.hw2.chat.User;

import java.util.ArrayList;
import java.util.List;

public final class UtilActionChat {

    private UtilActionChat() {
    }

    public static List<User> convertToSingleList(List<Chat> chatList) {
        List<User> newListWithUsers = new ArrayList<>();
        for (Chat nextChat : chatList) {
            for (User nextUser : nextChat.getUsers()) {
                if (nextUser.getAge() > 18) {
                    newListWithUsers.add(nextUser);
                }
            }
        }
        return newListWithUsers;
    }

    public static int calculateAverageAge(List<User> newListWithUsers) {
        int counter = 0;
        for (User next : newListWithUsers) {
            counter += next.getAge();
        }
        return counter / newListWithUsers.size();
    }
}
