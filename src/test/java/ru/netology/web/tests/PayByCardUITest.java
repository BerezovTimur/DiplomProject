package ru.netology.web.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.data.SQLHelper;
import ru.netology.web.pages.OrderPage;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PayByCardUITest {

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

    private String approved = "APPROVED";
    private String declined = "DECLINED";

    @Test
    void shouldPayIfValidCard() throws SQLException {
        OrderPage orderPage = new OrderPage();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getCurrentYear();
        String cardholder = DataHelper.getCardholder();
        String cvv = DataHelper.getCvv();
        orderPage.setPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
        orderPage.Success();
        String status = approved;
        String actualStatus = SQLHelper.getLastPaymentStatus();
        //assertEquals(status, actualStatus);
    }

    @Test
    void shouldNotPayIfValidCard() throws SQLException {
        OrderPage orderPage = new OrderPage();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getCurrentYear();
        String cardholder = DataHelper.getCardholder();
        String cvv = DataHelper.getCvv();
        orderPage.setPayment(DataHelper.getDeclinedCard(), month, year, cardholder, cvv);
        orderPage.Error();
        //SQLHelper.comparePaymentAndTransactionID();
        String status = declined;
        String actualStatus = SQLHelper.getLastPaymentStatus();
        assertEquals(status, actualStatus);
    }

    @Test
    void shouldPayIfAnotherCard() throws SQLException {
        OrderPage orderPage = new OrderPage();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getCurrentYear();
        String cardholder = DataHelper.getCardholder();
        String cvv = DataHelper.getCvv();
        orderPage.setPayment(DataHelper.getAnotherBankCard(), month, year, cardholder, cvv);
        orderPage.Success();
        //SQLHelper.comparePaymentAndTransactionID();
        String status = approved;
        String actualStatus = SQLHelper.getLastPaymentStatus();
        assertEquals(status, actualStatus);
    }
}
