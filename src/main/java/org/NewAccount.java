package org;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewAccount extends BasePage {

    private By newAccount = By.xpath("//a[normalize-space()='Open New Account']");

    private By accountType = By.xpath("//select[@id='type']");
    private By SAVINGS = By.xpath("//*[@id=\"type\"]/option[2]");

    private By openAccount = By.xpath("//*[@id=\"rightPanel\"]/div/div/form/div/input");
    private By messageSubt = By.xpath("//*[@id=\"rightPanel\"]/div/div/p[1]");
    String congratulations = "Congratulations, your account is now open.";

    public NewAccount(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void newAccountSAVINGS() throws InterruptedException {
        click(newAccount);
        click(accountType);
        wait.until(ExpectedConditions.elementToBeClickable(SAVINGS)).click();
    }

    public void openAccount() throws InterruptedException {
        click(openAccount);
    }

    public String getCongratulations() {
        return congratulations;
    }

    public By getMessageSubt() {
        return messageSubt;
    }
}
