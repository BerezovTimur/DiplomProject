package ru.netology.web.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.data.SQLHelper;
import ru.netology.web.pages.OrderPage;

import java.sql.SQLException;

public class PayByCardTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeAll
    static void cleanTable() throws SQLException {
        SQLHelper.cleanTable();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Nested
    public class NegativeTestsByNumberCard {

        @Test
        void shouldNotPayIfShortNumber() {
            OrderPage orderPage = new OrderPage();
            DataHelper DataHelper = new DataHelper();
            String month = DataHelper.getCurrentMonth();
            String year = DataHelper.getCurrentYear();
            String cardholder = DataHelper.getCardholder();
            String cvv = DataHelper.getCvv();
            orderPage.setPayment(DataHelper.getSmallNumberCard(), month, year, cardholder, cvv);
            orderPage.wrongFormatMassage();
        }

        @Test
        void shouldNotPayIfNoNumber() {
            OrderPage orderPage = new OrderPage();
            DataHelper DataHelper = new DataHelper();
            String month = DataHelper.getCurrentMonth();
            String year = DataHelper.getCurrentYear();
            String cardholder = DataHelper.getCardholder();
            String cvv = DataHelper.getCvv();
            orderPage.setPayment(DataHelper.getCardWithoutNumber(), month, year, cardholder, cvv);
            orderPage.wrongFormatMassage();
        }

        @Test
        void shouldNotPayIfWrongSymbolNoNumber() {
            OrderPage orderPage = new OrderPage();
            DataHelper DataHelper = new DataHelper();
            String month = DataHelper.getCurrentMonth();
            String year = DataHelper.getCurrentYear();
            String cardholder = DataHelper.getCardholder();
            String cvv = DataHelper.getCvv();
            orderPage.setPayment(DataHelper.getNotDigitalNumber(), month, year, cardholder, cvv);
            orderPage.wrongFormatMassage();
        }
    }

    @Nested
    public class NegativeTestsByMonth {

        @Test
        void shouldNotPayIfNoMonth() {
            OrderPage orderPage = new OrderPage();
            String month = DataHelper.getNoMonth();
            String year = DataHelper.getCurrentYear();
            String cardholder = DataHelper.getCardholder();
            String cvv = DataHelper.getCvv();
            orderPage.setPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
            orderPage.wrongFormatMassage();
        }

        @Test
        void shouldNotPayIfNullMonth() {
            OrderPage orderPage = new OrderPage();
            String month = DataHelper.getNullMonth();
            String year = DataHelper.getCurrentYear();
            String cardholder = DataHelper.getCardholder();
            String cvv = DataHelper.getCvv();
            orderPage.setPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
            orderPage.wrongFormatForMonthMassage();
        }

        @Test
        void shouldNotPayIfWrongMonth() {
            OrderPage orderPage = new OrderPage();
            String month = DataHelper.getWrongMonth();
            String year = DataHelper.getCurrentYear();
            String cardholder = DataHelper.getCardholder();
            String cvv = DataHelper.getCvv();
            orderPage.setPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
            orderPage.wrongFormatForMonthMassage();
        }

        @Test
        void shouldNotPayIfCardExpired() {
            OrderPage orderPage = new OrderPage();
            String month = DataHelper.getLastMonth();
            String year = DataHelper.getCurrentYear();
            String cardholder = DataHelper.getCardholder();
            String cvv = DataHelper.getCvv();
            orderPage.setPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
            orderPage.wrongFormatForMonthMassage();
        }
    }

    @Nested
    public class NegativeTestsByYear {

        @Test
        void shouldNotPayIfNoYear() {
            OrderPage orderPage = new OrderPage();
            String month = DataHelper.getCurrentMonth();
            String year = DataHelper.getNoYear();
            String cardholder = DataHelper.getCardholder();
            String cvv = DataHelper.getCvv();
            orderPage.setPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
            orderPage.wrongFormatMassage();
        }

        @Test
        void shouldNotPayIfNullYear() {
            OrderPage orderPage = new OrderPage();
            String month = DataHelper.getCurrentMonth();
            String year = DataHelper.getNullYear();
            String cardholder = DataHelper.getCardholder();
            String cvv = DataHelper.getCvv();
            orderPage.setPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
            orderPage.invalidCardMassage();
        }

        @Test
        void shouldNotPayIfLastYear() {
            OrderPage orderPage = new OrderPage();
            String month = DataHelper.getCurrentMonth();
            String year = DataHelper.getLastYear();
            String cardholder = DataHelper.getCardholder();
            String cvv = DataHelper.getCvv();
            orderPage.setPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
            orderPage.invalidCardMassage();
        }

        @Test
        void shouldPayIfWrongYear() {
            OrderPage orderPage = new OrderPage();
            String month = DataHelper.getCurrentMonth();
            String year = DataHelper.getMaxValidDate();
            String cardholder = DataHelper.getCardholder();
            String cvv = DataHelper.getCvv();
            orderPage.setPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
            orderPage.wrongFormatForMonthMassage();
        }
    }

    @Nested
    public class NegativeTestsByCVV {

        @Test
        void shouldNotPayIfNoCVV() {
            OrderPage orderPage = new OrderPage();
            String month = DataHelper.getCurrentMonth();
            String year = DataHelper.getCurrentYear();
            String cardholder = DataHelper.getCardholder();
            String cvv = DataHelper.getNoCvv();
            orderPage.setPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
            orderPage.wrongFormatMassage();
        }

        @Test
        void shouldNotPayIfWrongCVV() {
            OrderPage orderPage = new OrderPage();
            String month = DataHelper.getCurrentMonth();
            String year = DataHelper.getCurrentYear();
            String cardholder = DataHelper.getCardholder();
            String cvv = DataHelper.getWrongCvv();
            orderPage.setPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
            orderPage.wrongFormatMassage();
        }
    }

    @Nested
    public class NegativeTestsByCardholder {

        @Test
        void shouldNotPayIfNoName() {
            OrderPage orderPage = new OrderPage();
            String month = DataHelper.getCurrentMonth();
            String year = DataHelper.getCurrentYear();
            String cardholder = DataHelper.getNoCardholder();
            String cvv = DataHelper.getCvv();
            orderPage.setPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
            orderPage.cardholderNameMassage();
        }

        @Test
        void shouldNotPayIfWrongName() {
            OrderPage orderPage = new OrderPage();
            String month = DataHelper.getCurrentMonth();
            String year = DataHelper.getCurrentYear();
            String cardholder = DataHelper.getCardholderRussianLatter();
            String cvv = DataHelper.getCvv();
            orderPage.setPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
            orderPage.cardholderNameMassage();
        }

        @Test
        void shouldNotPayIfOnlyName() {
            OrderPage orderPage = new OrderPage();
            String month = DataHelper.getCurrentMonth();
            String year = DataHelper.getCurrentYear();
            String cardholder = DataHelper.getOnlyNameCardholder();
            String cvv = DataHelper.getCvv();
            orderPage.setPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
            orderPage.cardholderNameMassage();
        }
    }
}
