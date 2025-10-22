import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement amountInput = $("[data-test-id=amount] input");
    private SelenideElement fromInput = $("[data-test-id=from] input");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private SelenideElement errorNotification = $("[data-test-id=error-notification]");

    public DashboardPage makeValidTransfer(String amount, String fromCardNumber) {
        amountInput.setValue(amount);
        fromInput.setValue(fromCardNumber);
        transferButton.click();
        return new DashboardPage();
    }

    public void makeInvalidTransfer(String amount, String fromCardNumber) {
        amountInput.setValue(amount);
        fromInput.setValue(fromCardNumber);
        transferButton.click();
        errorNotification.shouldBe(Condition.visible);
    }
}