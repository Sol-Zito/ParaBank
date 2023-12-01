package org;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TransFerFounds extends BasePage {

    /*Transferir Fondos
    ○ Haga clic en <Transferencia de fondos>
    ○ Compruebe que el texto "Transferir fondos" es visible en la pantalla
    ○ En el campo <Importe: $> introduzca el importe a transferir
    ○ En el campo <De la cuenta #> seleccione una cuenta
    ○ En el campo <a la cuenta #> seleccione una cuenta distinta a la anterior
    ○ Haga clic en <Transferencia>
    ○ Compruebe que el texto "¡Transferencia completa!" es visible en la pantalla*/

    private By TransferFounds = By.xpath("//a[normalize-space()='Transfer Funds']");

    public By getTitle() {
        return Title;
    }

    private By Title = By.xpath("//*[@id=\"rightPanel\"]/div/div/h1");

    private By Amount = By.id("amount");

    private By FromAccount = By.xpath("//*[@id=\"fromAccountId\"]");
    private By Account1 = By.xpath("//*[@id=\"fromAccountId\"]/option[1]");
    private By Account2 = By.xpath("//*[@id=\"fromAccountId\"]/option[2]");

    private By ToAccount = By.id("toAccountId");
    private By Account1a = By.xpath("//*[@id=\"toAccountId\"]/option[1]");
    private By Account2a = By.xpath("//*[@id=\"toAccountId\"]/option[2]");

    private By BtnTransfer = By.xpath("//input[@value='Transfer']");

    private By Message = By.xpath("//*[@id=\"rightPanel\"]/div/div/h1");

    public TransFerFounds(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void transferbtn() throws InterruptedException {
        click(TransferFounds);
    }

    public void startProcess(String amount) throws InterruptedException {
        sendText(amount, Amount);
    }

    public void fromAccount(int fromAccount) throws InterruptedException {
        elementFind(FromAccount).click();
        if (fromAccount == 1){
            wait.until(ExpectedConditions.elementToBeClickable(Account1)).click();
        }else if (fromAccount == 2){
            wait.until(ExpectedConditions.elementToBeClickable(Account2)).click();
        }
    }
    public void fromAccount() throws InterruptedException {
        elementFind(FromAccount).click();
        wait.until(ExpectedConditions.elementToBeClickable(Account2)).click();

    }

    public void toAccount(int toAccount) throws InterruptedException {
        elementFind(ToAccount).click();
        if (toAccount == 1){
            wait.until(ExpectedConditions.elementToBeClickable(Account1a)).click();
        }else if (toAccount == 2){
            wait.until(ExpectedConditions.elementToBeClickable(Account2a)).click();
        }
    }
    public void toAccount() throws InterruptedException {
        elementFind(ToAccount).click();

        wait.until(ExpectedConditions.elementToBeClickable(Account1a)).click();

    }

    public void finishProcess() throws InterruptedException {
        click(BtnTransfer);
    }

    public By getMessage() {
        return Message;
    }
}
