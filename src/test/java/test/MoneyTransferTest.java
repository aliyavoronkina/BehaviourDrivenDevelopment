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

        var firstCardBalance = dashboardPage.getCardBalance("92df3f1c-a033-48e6-8390-206f6b1f56c0");
        var secondCardBalance = dashboardPage.getCardBalance("0f3f5c2a-249e-4c3d-8287-09f7a039391d");

        var transferAmount = secondCardBalance / 2;

        var transferPage = dashboardPage.transferToFirstCard();
        transferPage.makeTransfer(transferAmount, "5559 0000 0000 0002");
    }
}