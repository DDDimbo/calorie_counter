package com.winesmoker.calorie.counter.complaint.service;

import com.winesmoker.calorie.counter.complaint.Complaint;
import com.winesmoker.calorie.counter.complaint.ComplaintRepository;
import com.winesmoker.calorie.counter.exceptions.UserNotFoundException;
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
        complaintRepository.save(Complaint.builder()
                .description(message.getText())
                .initiator(userRepository.findIdByTgId(message.getFrom().getId())
                        .orElseThrow(() -> new UserNotFoundException("Пользователь не зарегистрирован")))
                .build());
        String answer = "Сообщение успешно отправлено разработчику ✈";
        return answer;
    }
}
