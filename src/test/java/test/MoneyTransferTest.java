package test;

import data.AuthInfo;
import data.DataHelper;
import page.DashboardPage;
import page.LoginPage;
import page.TransferPage;
import page.VerificationPage;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @Test
    void shouldTransferMoneyFromSecondToFirst() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);

        var firstCardBalance = dashboardPage.getFirstCardBalance();
        var secondCardBalance = dashboardPage.getSecondCardBalance();

        var transferPage = dashboardPage.transferToFirstCard();
        int amount = 1000;
        transferPage.makeTransfer(amount, DataHelper.getOtherAuthInfo().getLogin());

        var expectedFirstCardBalance = firstCardBalance + amount;
        var expectedSecondCardBalance = secondCardBalance - amount;

        assertEquals(expectedFirstCardBalance, dashboardPage.getFirstCardBalance());
        assertEquals(expectedSecondCardBalance, dashboardPage.getSecondCardBalance());
    }
}