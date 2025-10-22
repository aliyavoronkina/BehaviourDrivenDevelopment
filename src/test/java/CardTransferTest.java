import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTransferTest {

    @BeforeEach
    void setup() {
        Configuration.headless = false; // ВРЕМЕННО ОТКЛЮЧИТЬ HEADLESS
        Configuration.browser = "chrome";
        Configuration.browserSize = "1366x768";
        open("http://localhost:9999");
    }

    private DashboardPage login() {
        LoginPage loginPage = new LoginPage();
        DataHelper.AuthInfo authInfo = DataHelper.getAuthInfo();
        VerificationPage verificationPage = loginPage.validLogin(authInfo);
        DataHelper.VerificationCode verificationCode = DataHelper.getVerificationCode();
        return verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCards() {
        DashboardPage dashboardPage = login();

        DataHelper.CardInfo firstCard = DataHelper.getFirstCard();
        DataHelper.CardInfo secondCard = DataHelper.getSecondCard();

        int firstCardBalanceBefore = dashboardPage.getCardBalance(firstCard.getNumber());
        int secondCardBalanceBefore = dashboardPage.getCardBalance(secondCard.getNumber());

        int transferAmount = 1000;
        TransferPage transferPage = dashboardPage.selectCardToTransfer(secondCard.getNumber());
        dashboardPage = transferPage.makeValidTransfer(String.valueOf(transferAmount), firstCard.getNumber());

        int firstCardBalanceAfter = dashboardPage.getCardBalance(firstCard.getNumber());
        int secondCardBalanceAfter = dashboardPage.getCardBalance(secondCard.getNumber());

        assertEquals(firstCardBalanceBefore - transferAmount, firstCardBalanceAfter);
        assertEquals(secondCardBalanceBefore + transferAmount, secondCardBalanceAfter);
    }
}