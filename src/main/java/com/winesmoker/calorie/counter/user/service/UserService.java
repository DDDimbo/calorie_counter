package com.winesmoker.calorie.counter.user.service;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface UserService {

    String createCheck(Message message);
}
