package com.winesmoker.calorie.counter.complaint.service;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface ComplaintService {

    String create(Message message);
}
