package org;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

public class CheckAccount extends BasePage{
    /*Resumen de las cuentas
    ○ Haga clic en <Resumen de cuentas>
    ○ Compruebe si el texto "*El saldo incluye depósitos que pueden estar sujetos a
    retenciones" está visible en la pantalla*/


    private By MessageXPath = By.xpath("//*[@id=\"accountTable\"]/tfoot/tr/td");
    private String message = "*Balance includes deposits that may be subject to holds";


    /*Actividad de la cuenta (cada mes)
    ○ Haga clic en <Resumen de cuentas>
    ○ Compruebe que el texto "*El saldo incluye depósitos que pueden estar sujetos a
    retenciones" es visible en la pantalla

    ○ Haga clic en una cuenta en la columna <Cuenta>.
    ○ Compruebe que el texto "Detalles de la cuenta" es visible en la pantalla
    ○ En "Actividad de la cuenta" haga clic en <Periodo de actividad:> y seleccione all
    ○ En "Actividad de la cuenta" haga clic en <Tipo:> y seleccione all
    ○ Haga clic en <Ir>*/

    private By AccountOne = By.xpath("//*[@id=\"accountTable\"]/tbody/tr[1]/td[1]/a");
    private By AccountTwo = By.xpath("//*[@id=\"accountTable\"]/tbody/tr[1]/td[2]");

    private By TitleDetailXpath = By.xpath("//*[@id=\"rightPanel\"]/div/div[1]/h1");
    private String TitleDetail = "Account Details";

    private By ActivityPeriod = By.id("month");
    private By AllPeriod = By.xpath("//*[@id=\"month\"]/option[1]");
    private By January = By.xpath("//*[@id=\"month\"]/option[2]");

    private By Type = By.id("transactionType");
    private By TypeAll = By.xpath("//*[@id=\"transactionType\"]/option[1]");
    //debit o credit

    private By BtnGo = By.xpath("//input[@value='Go']");

    private By Details = By.xpath("//*[@id=\"transactionTable\"]/tbody/tr/td[2]/a");
    private String NotFound = "No transactions found.";

    public CheckAccount(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void selectAccount(String account) throws InterruptedException {
        if (Objects.equals(account, "one")) {
            click(AccountOne);
        } else { click(AccountTwo);}
    }

    public void selectPeriod (String month) throws InterruptedException {
        click(ActivityPeriod);
        if(Objects.equals(month, "todos") || Objects.equals(month, "all")) {
            wait.until(ExpectedConditions.elementToBeClickable(AllPeriod)).click();
        } else {click(January);}

        click(Type);
        wait.until(ExpectedConditions.elementToBeClickable(TypeAll));

        click(BtnGo);
    }

    public String information() throws InterruptedException {
        if (elementFind(Details) != null ){
            return "Funds Transfer Sent";
        }else {
            return NotFound;
        }
    }

    public By getTitleDetailXpath() {
        return TitleDetailXpath;
    }

    public String getTitleDetail() {
        return TitleDetail;
    }

    public By getMessageXPath() {
        return MessageXPath;
    }

    public String getMessage() {
        return message;
    }
}

