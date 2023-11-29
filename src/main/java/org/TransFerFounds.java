package org;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TransFerFounds extends BasePage {

    /*Transferir Fondos
    ○ Haga clic en <Transferencia de fondos>
    ○ Compruebe que el texto "Transferir fondos" es visible en la pantalla
    ○ En el campo <Importe: $> introduzca el importe a transferir
    ○ En el campo <De la cuenta #> seleccione una cuenta
    ○ En el campo <a la cuenta #> seleccione una cuenta distinta a la anterior
    ○ Haga clic en <Transferencia>
    ○ Compruebe que el texto "¡Transferencia completa!" es visible en la pantalla*/

    private By TransferFounds = By.xpath("//a[normalize-space()='Transfer Funds']"); //btn TransferFounds
    private By Title = By.xpath("//*[@id=\"rightPanel\"]/div/div/h1"); //Transfer Funds
    private By Amount = By.id("amount"); //xpath("//input[@id='amount']"); //ingresar el amount

    private By FromAccount = By.id("fromAccountId"); // //select[@id='fromAccountId']
    private By Account1 = By.xpath("//*[@id=\"fromAccountId\"]/option[1]");
    private By Account2 = By.xpath("//*[@id=\"fromAccountId\"]/option[2]");

    private By ToAccount = By.id("toAccountId"); //xpath("//*[@id="toAccountId"]") //select[@id='toAccountId']
    private By Account1a = By.xpath("//*[@id=\"toAccountId\"]/option[1]");
    private By Account2a = By.xpath("//*[@id=\"toAccountId\"]/option[2]");

    private By BtnTransfer = By.xpath("//input[@value='Transfer']");

    private By Message = By.xpath("//*[@id=\"rightPanel\"]/div/div/h1"); //h1[@class='title']

    public TransFerFounds(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }
}
