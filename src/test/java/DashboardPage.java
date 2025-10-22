import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public int getCardBalance(String cardNumber) {
        String last4Digits = cardNumber.substring(15);
        var text = cards.findBy(com.codeborne.selenide.Condition.text(last4Digits)).text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish).trim();
        value = value.replace(" ", "");
        return Integer.parseInt(value);
    }

    public TransferPage selectCardToTransfer(String cardNumber) {
        String last4Digits = cardNumber.substring(15);
        cards.findBy(com.codeborne.selenide.Condition.text(last4Digits)).$("button").click();
        return new TransferPage();
    }
}