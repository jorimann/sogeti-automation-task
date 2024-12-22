package org.example.sogetiautomationtask.ui.factory;

import com.github.javafaker.Faker;
import org.example.sogetiautomationtask.ui.models.Message;
import org.example.sogetiautomationtask.ui.enums.Country;
import org.example.sogetiautomationtask.ui.enums.PositionLevel;
import org.example.sogetiautomationtask.ui.enums.PurposeOfContact;

import java.util.Locale;
import java.util.Random;

public class MessageFactory {
    public static Message getMessage() {
        Faker faker = new Faker(new Locale("en", "US"));
        String email = faker.bothify("????##@gmail.com");
        return new Message(getRandomEnum(PurposeOfContact.class),
                "automation test execution",
                faker.name().firstName(),
                faker.name().lastName(),
                faker.job().title(),
                getRandomEnum(PositionLevel.class),
                email,
                getRandomEnum(Country.class),
                faker.phoneNumber().subscriberNumber(10),
                faker.company().name(),
                "This is practice task, please ignore this message");
    }

    private static <E extends Enum<E>> E getRandomEnum(Class<E> enumClass) {
        Random random = new Random();
        E[] enumValues = enumClass.getEnumConstants();
        return enumValues[random.nextInt(enumValues.length)];
    }
}
