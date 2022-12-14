package com.winesmoker.calorie.counter;

import com.winesmoker.calorie.counter.complaint.service.ComplaintService;
import com.winesmoker.calorie.counter.enums.MealIntake;
import com.winesmoker.calorie.counter.meal.service.MealService;
import com.winesmoker.calorie.counter.service.MealModeService;
import com.winesmoker.calorie.counter.user.service.UserService;
import com.winesmoker.calorie.counter.utility.Prints;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class TestBot extends TelegramLongPollingBot {

    private final UserService userService;
    private final ComplaintService complaintService;
    private final MealService mealService;

    private final MealModeService mealModeService = MealModeService.getInstance();


    @Autowired
    @SneakyThrows
    public TestBot(UserService userService, ComplaintService complaintService, MealService mealService) {
        this.userService = userService;
        this.complaintService = complaintService;
        this.mealService = mealService;

        final List<BotCommand> botCommands = new ArrayList<>();
        botCommands.add(new BotCommand("/start", "Opening remarks"));
        botCommands.add(new BotCommand("/help", "Information about bot works"));
        botCommands.add(new BotCommand("/calculate_meal_intake", "Add meal and get calories"));
        botCommands.add(new BotCommand("/get_history", "Get information for some period"));
        botCommands.add(new BotCommand("/clear_history", "Clear your calories history"));
        botCommands.add(new BotCommand("/donate", "For donates"));
        botCommands.add(new BotCommand("/for_developers", "Help improve the work of the bot"));
        execute(new SetMyCommands(botCommands, new BotCommandScopeDefault(), null));
    }


    @Override
    public String getBotUsername() {
        return "@trustedDisputeBot";
    }

    @Override
    public String getBotToken() {
        return "5564991119:AAFsnmqvffIfgfagVcdO4SLz-RsX0vbmLRs";
    }

//  @Override
//  public String getBotUsername() {
//    return "@bestCaloriesCounterEverBot";
//  }
//
//  @Override
//  public String getBotToken() {
//    return "5854681113:AAHnxDEuZARcDoB7b3RaoBcyGJ-o82v3WR4";
//  }


    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        if (update.hasCallbackQuery()) {
            handleCallback(update.getCallbackQuery());
        } else if (update.hasMessage()) {
            handleMessage(update.getMessage());
        }
    }

    @SneakyThrows
    private void handleCallback(CallbackQuery callbackQuery) {
        Message message = callbackQuery.getMessage();
        String[] param = callbackQuery.getData().split(":");
        String action = param[0];
        MealIntake newMeal = MealIntake.valueOf(param[1]);

        switch (action) {
            case "MEAL":
                mealModeService.setTargetMeal(message.getChatId(), newMeal);

        }
        List<InlineKeyboardButton> mealButtons = new ArrayList<>();
        MealIntake targetMeal = mealModeService.getTargetMeal(message.getChatId());
        for (MealIntake meal : MealIntake.values()) {
            mealButtons.add(
                    InlineKeyboardButton.builder()
                            .text(getCurrencyButton(targetMeal, meal))
                            .callbackData("MEAL:" + meal.name())
                            .build()
            );
        }
        execute(EditMessageReplyMarkup.builder()
                .chatId(message.getChatId().toString())
                .messageId(message.getMessageId())
                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(Collections.singleton(mealButtons)).build())
                .build());
    }

    @SneakyThrows
    private void handleMessage(Message message) {
        // handle command
        if (message.hasText() && message.hasEntities()) {
            Optional<MessageEntity> commandEntity =
                    message.getEntities().stream().filter(e -> "bot_command".equals(e.getType())).findFirst();
            if (commandEntity.isPresent()) {
                String command = message
                        .getText()
                        .substring(commandEntity.get().getOffset(), commandEntity.get().getLength());
                switch (command) {
                    case "/start":
                        execute(SendMessage.builder()
                                .text(userService.createCheck(message))
                                .chatId(message.getChatId().toString())
                                .build());
                        break;
                    case "/help":
                        execute(SendMessage.builder()
                                .text(Prints.helpMessage)
                                .chatId(message.getChatId().toString())
                                .build());
                        break;
                    case "/get_history":
                    case "/clear_history":
//                    case "/donate":
                    case "/calculate_meal_intake":
                        List<InlineKeyboardButton> mealButtons = new ArrayList<>();
                        MealIntake targetMeal = mealModeService.getTargetMeal(message.getChatId());
                        System.out.println(message.getChatId());
                        for (MealIntake meal : MealIntake.values()) {
                            mealButtons.add(
                                    InlineKeyboardButton.builder()
                                            .text(getCurrencyButton(targetMeal, meal))
                                            .callbackData("MEAL:" + meal.name())
                                            .build()
                            );
                        }
                        execute(SendMessage.builder()
                                .text("Please choose meal time")
                                .chatId(message.getChatId().toString())
                                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(List.of(mealButtons)).build())
                                .build());
                        execute(SendMessage.builder()
                                .text()
                                .chatId(message.getChatId().toString())
                                .build());
                        createMeal(message, mealModeService.getTargetMeal(message.getChatId()));
                        return;
                    case "/for_developers":
                        execute(SendMessage.builder()
                                .text(userService.createCheck(message))
                                .chatId(message.getChatId().toString())
                                .build());
                    default:
                        execute(SendMessage.builder()
                                .text("Command not found ⚠")
                                .chatId(message.getChatId().toString())
                                .build());
                }

            }
        }

    }

    private void createMeal(Message message, MealIntake meal) {
        if (message.hasText()) {
            String messageText = message.getText();
            MealIntake targetMeal = mealModeService.getTargetMeal(message.getChatId());
            String answer = mealService.create(message, meal);
        }
    }

    private String getCurrencyButton(MealIntake saved, MealIntake meal) {
        return saved == meal ? meal + " ✅" : meal.name();
    }

}
