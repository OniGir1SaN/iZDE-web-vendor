package com.demoqa.utils.iZDEvendor;

import com.demoqa.entities.iZDEvendor.CreateObjectEntity.FacilityDescriptionEntity;
import com.demoqa.entities.iZDEvendor.CreateObjectEntity.RegulationsEntity;
import com.demoqa.entities.iZDEvendor.LoginEntityV;
import com.demoqa.utils.ConfigReader;
import com.github.javafaker.Faker;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

public class RandomUtilsV {

    private static final Faker faker = new Faker();
    private static final Random random = new SecureRandom();

    public LoginEntityV validLoginEntity() {
        LoginEntityV entity = new LoginEntityV();
        entity.setEmailOrPhone(ConfigReader.getValue("number"));
        entity.setPassword(ConfigReader.getValue("password"));
        return entity;
    }

    public LoginEntityV generateRandomLoginEntityV() {
        LoginEntityV loginEntityV = new LoginEntityV();
        loginEntityV.setEmailOrPhone(faker.internet().emailAddress());
        loginEntityV.setPassword(generateRandomPassword());
        return loginEntityV;
    }

    public FacilityDescriptionEntity generateRandomFacilityDescriptionEntity() {
        FacilityDescriptionEntity facilityDescriptionEntity = new FacilityDescriptionEntity();
        facilityDescriptionEntity.setObjectNameInput(faker.company().name());
        facilityDescriptionEntity.setObjectDescriptionInput(faker.lorem().sentence());
        return facilityDescriptionEntity;
    }

    public FacilityDescriptionEntity generateRandomPicture(int count) {
        String[] pictures = {
                "D:\\Program Files\\iZDE\\src\\main\\resources\\pictures\\0105b5a8865355f0c551606c4fee9120.jpg",
                "D:\\Program Files\\iZDE\\src\\main\\resources\\pictures\\6049c7e837421db8e529d013c715beb5.jpg",
                "D:\\Program Files\\iZDE\\src\\main\\resources\\pictures\\5801864208709694499_119.jpg",
                "D:\\Program Files\\iZDE\\src\\main\\resources\\pictures\\dhmjfxx-3e30ef99-c1b0-4b61-9218-332ff3674415.png",
                "D:\\Program Files\\iZDE\\src\\main\\resources\\pictures\\Gearhead Girls.jpg",
                "D:\\Program Files\\iZDE\\src\\main\\resources\\pictures\\illustration-manga-cartoon-samurai-Miyamoto-Musashi-comics-122074-wallhere.com.jpg",
                "D:\\Program Files\\iZDE\\src\\main\\resources\\pictures\\Mastery (masterydivine) - Profile _ Pinterest.jpg",
                "D:\\Program Files\\iZDE\\src\\main\\resources\\pictures\\Rad Racer.jpg",
                "D:\\Program Files\\iZDE\\src\\main\\resources\\pictures\\Без названия.jpg",
                "D:\\Program Files\\iZDE\\src\\main\\resources\\pictures\\Без названия (1).jpg",
                "D:\\Program Files\\iZDE\\src\\main\\resources\\pictures\\Без названия (2).jpg"
        };

        FacilityDescriptionEntity entity = new FacilityDescriptionEntity();
        entity.setDownloadPictures(Arrays.copyOfRange(pictures, 0, count));
        return entity;
    }

    public RegulationsEntity generateRegulations() {
        RegulationsEntity regulationsEntity = new RegulationsEntity();
        regulationsEntity.setRegulationsInput(faker.lorem().sentence());
        regulationsEntity.setCancellationInput(faker.lorem().sentence());

        return regulationsEntity;
    }


    public static String generateRandomPassword() {
        String upperCase = getRandomChars("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 2);
        String lowerCase = getRandomChars("abcdefghijklmnopqrstuvwxyz", 2);
        String digits = getRandomChars("0123456789", 2);
        String specialChars = getRandomChars("!@#$%^&*()_+", 2);
        String remaining = getRandomChars("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+", 4);
        return shuffleString(upperCase + lowerCase + digits + specialChars + remaining);
    }

    private static String getRandomChars(String characters, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }

    private static String shuffleString(String input) {
        char[] array = input.toCharArray();
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        return new String(array);
    }
}
