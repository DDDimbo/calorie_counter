package com.winesmoker.calorie.counter.complaint.service;

import com.winesmoker.calorie.counter.complaint.Complaint;
import com.winesmoker.calorie.counter.complaint.ComplaintRepository;
import com.winesmoker.calorie.counter.exceptions.UserNotFoundException;
import com.winesmoker.calorie.counter.user.User;
import com.winesmoker.calorie.counter.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
@Transactional
@RequiredArgsConstructor
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;

    private final UserRepository userRepository;

    @Override
    public String create(Message message) {
        User tgUser = userRepository.findByTgId(message.getFrom().getId())
                .orElseThrow(() -> new UserNotFoundException("Пользователя с таким tgId не существует"));
        complaintRepository.save(Complaint.builder()
                .description(message.getText())
                .initiator(tgUser.getId())
                .build());
        return "Сообщение успешно отправлено разработчику ✈";
    }
}
