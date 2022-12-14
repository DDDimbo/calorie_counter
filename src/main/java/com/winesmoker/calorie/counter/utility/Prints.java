package com.winesmoker.calorie.counter.utility;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Prints {

    public static final String helpMessage =
            "\uD83D\uDE43 Приветствуем тебя! Наверное ты часто задавал вопрос зачем считать калории?\n\n" +
                    "\uD83E\uDD14 Употребление пищи — одно из доступных удовольствий для каждого человека. " +
                    "Сегодня, когда уровень кулинарного мастерства сопоставим с искусством, " +
                    "многие блюда и продукты питания кажутся настолько вкусными, что некоторые люди могут забывают о чувстве меры.\n\n" +
                    "\uD83E\uDD9B Как результат, лишние калории, полученные вместе с ними, откладываются на теле." +
                    "А лишний вес — один из триггеров проблем со здоровьем.\n\n" +
                    "\uD83E\uDDEE Умение считать калории позволит держать под контролем аппетит, не переедать, а значит, и сохранять свое здоровье.\n\n" +
                    "\uD83E\uDD13 Этот бот позволит тебе рассчитать количество калорий, будет хранить твои данные и " +
                    "позволит получить их за различные промежутки времени.\n\n" +
                    "\uD83E\uDDF9 Также при необходимости ты можешь очистить историю своих записей. \n\n" +
                    "\uD83E\uDD16 В случае обнаружении проблем в работе бота или нахождении багов, можешь отправить свое сообщение разработчику. \n\n" +
                    "\uD83D\uDCD6 Он обязательно его прочтет! (ответы не предусмотрены) \n\n" +
                    "Удачи! Надеюсь тебе тут понравится ❤";

    public static final String addCalorieMessage =
            "Введите название блюда или напитка вместе с данными для расчета в следующем формате: \n" +
                    "\"Название белки углеводы жиры клетчатка алкоголь\"";
}
