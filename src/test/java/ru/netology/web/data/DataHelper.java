package ru.netology.web.data;

import com.github.javafaker.Faker;
import lombok.Value;
import lombok.val;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {

    @Value
    public static class CardNumber {
        private String CardNumber;
    }

    public static CardNumber getApprovedCard() {
        return new CardNumber("4444444444444441");
    }

    public static CardNumber getDeclinedCard() {
        return new CardNumber("4444444444444442");
    }

    public static CardNumber getAnotherBankCard() {
        return new CardNumber("5555555555555555");
    }

    public static CardNumber getCardWithoutNumber() {
        return new CardNumber("");
    }

    public static CardNumber getSmallNumberCard() {
        return new CardNumber("444444444444444");
    }

    public static CardNumber getNotDigitalNumber() {
        return new CardNumber("aaaaaaaaaaaaaaaa");
    }

    //Данные для тестов по месяцу

    public static String getCurrentMonth() {
        val month = YearMonth.now().format(DateTimeFormatter.ofPattern("MM"));
        return month;
    }

    public static String getLastMonth() {
        val month = YearMonth.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
        return month;
    }

    public static String getWrongMonth() {
        return "13";
    }

    public static String getNoMonth() {
        return "";
    }

    public static String getNullMonth() {
        return "00";
    }

    //Данные для тестов по году

    public static String getCurrentYear() {
        val year = YearMonth.now().format(DateTimeFormatter.ofPattern("yy"));
        return year;
    }

    public static String getLastYear() {
        val year = YearMonth.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
        return year;
    }

    public static String getMaxValidDate() {
        val year = YearMonth.now().plusYears(6).format(DateTimeFormatter.ofPattern("yy"));
        return year;
    }

    public static String getNoYear() {
        return "";
    }

    public static String getNullYear() {
        return "00";
    }

    //Данные для тестов по владельцу

    public static String getCardholder() {
        Faker faker = new Faker(new Locale("en"));
        val cardholder = faker.name().firstName() + " " + faker.name().lastName();
        return cardholder;
    }

    public static String getCardholderRussianLatter() {
        Faker faker = new Faker(new Locale("ru"));
        val cardholder = faker.name().firstName() + " " + faker.name().lastName();
        return cardholder;
    }

    public static String getOnlyNameCardholder() {
        Faker faker = new Faker(new Locale("en"));
        val cardholder = faker.name().firstName();
        return cardholder;
    }

    public static String getNoCardholder() {
        return "";
    }

    //Данные для тестов по CVV

    public static String getCvv() {
        return "456";
    }

    public static String getWrongCvv() {
        return "45";
    }

    public static String getNoCvv() {
        return "";
    }
}
