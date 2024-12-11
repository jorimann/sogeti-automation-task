package org.example.sogetiautomationtask.utils;

import com.github.javafaker.Faker;
import org.example.sogetiautomationtask.dto.Country;
import org.example.sogetiautomationtask.dto.Message;
import org.example.sogetiautomationtask.dto.PositionLevel;
import org.example.sogetiautomationtask.dto.PurposeOfContact;

import java.util.Locale;
import java.util.Random;

public class MessageFactory {
    Message message;
    public static Message getMessage(){
        Faker faker = new Faker(new Locale("en", "US"));
        String email = faker.bothify("????##@gmail.com");
        return new Message( getRandomEnum(PurposeOfContact.class).getName(),
                faker.name().firstName(),
                faker.name().lastName(),
                faker.job().title(),
                getRandomEnum(PositionLevel.class).getName(),
                email,
                getRandomEnum(Country.class).getName(),
                faker.phoneNumber().subscriberNumber(10),
                faker.company().name(),
                "This is practice task, please ignore this message");
    }



    private static PurposeOfContact getRandomPurpose() {
        Random random = new Random();
        PurposeOfContact[] purposes = PurposeOfContact.values(); // Получаем все значения из enum
        return purposes[random.nextInt(purposes.length)]; // Возвращаем случайный день
    }

    private static PositionLevel getRandomPositionLevel() {
        Random random = new Random();
        PositionLevel[] positionLevels = PositionLevel.values(); // Получаем все значения из enum
        return positionLevels[random.nextInt(positionLevels.length)]; // Возвращаем случайный день
    }

    private static <E extends Enum<E>> E getRandomEnum(Class<E> enumClass){
        Random random = new Random();
        E[] enumValues = enumClass.getEnumConstants();
        return enumValues[random.nextInt(enumValues.length)];
    }
}
