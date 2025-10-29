package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement firstCard = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    private SelenideElement secondCard = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");
    private SelenideElement firstCardButton = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] button");
    private SelenideElement secondCardButton = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] button");

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public int getFirstCardBalance() {
        var text = firstCard.getText();
        return extractBalance(text);
    }

    public int getSecondCardBalance() {
        var text = secondCard.getText();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        var balanceStart = text.indexOf("баланс:") + 8;
        var balanceEnd = text.indexOf("р.", balanceStart);
        return Integer.parseInt(text.substring(balanceStart, balanceEnd).trim());
    }

    public TransferPage transferToFirstCard() {
        firstCardButton.click();
        return new TransferPage();
    }

    public TransferPage transferToSecondCard() {
        secondCardButton.click();
        return new TransferPage();
    }
}