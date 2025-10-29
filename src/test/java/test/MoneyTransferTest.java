package test;

import data.AuthInfo;
import page.DashboardPage;
import page.LoginPage;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {

    @Test
    void shouldTransferMoneyFromSecondToFirst() {
        open("http://localhost:9999");

        var loginPage = new LoginPage();
        var authInfo = new AuthInfo("vasya", "qwerty123");
        var verificationPage = loginPage.validLogin(authInfo);
        var dashboardPage = verificationPage.validVerify("12345");

        int firstCardBalance = dashboardPage.getFirstCardBalance();
        int secondCardBalance = dashboardPage.getSecondCardBalance();

        int amount = secondCardBalance / 2;

        var transferPage = dashboardPage.transferToFirstCard();
        transferPage.makeTransfer(amount, "5559 0000 0000 0002");
    }
}