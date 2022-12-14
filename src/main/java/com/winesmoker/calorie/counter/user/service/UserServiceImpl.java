package com.winesmoker.calorie.counter.user.service;

import com.winesmoker.calorie.counter.user.User;
import com.winesmoker.calorie.counter.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public String createCheck(Message message) {
        if (userRepository.existsByTgId(message.getFrom().getId()))
            return "С возращением " + message.getFrom().getUserName();
        else {
            userRepository.save(User.builder()
                    .tgId(message.getFrom().getId())
                    .authDate(LocalDate.now())
                    .build());
            return "Рады приветствовать нового пользователя " + message.getFrom().getUserName() + "! \n" +
                    "Надеемся этот бот станет для вас полезным!";
        }
    }
}
