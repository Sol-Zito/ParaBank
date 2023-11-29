package org;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckAccount extends BasePage{
    /*Resumen de las cuentas
    ○ Haga clic en <Resumen de cuentas>
    ○ Compruebe si el texto "*El saldo incluye depósitos que pueden estar sujetos a
    retenciones" está visible en la pantalla*/

    private By Overview = By.xpath("//a[normalize-space()='Accounts Overview']"); //btn Overview
    private By Message = By.xpath("//*[@id=\"accountTable\"]/tfoot/tr/td");
    private String ready = "Balance includes deposits that may be subject to holds";

    /*Actividad de la cuenta (cada mes)
    ○ Haga clic en <Resumen de cuentas>
    ○ Compruebe que el texto "*El saldo incluye depósitos que pueden estar sujetos a
    retenciones" es visible en la pantalla
    ○ Haga clic en una cuenta en la columna <Cuenta>.
    ○ Compruebe que el texto "Detalles de la cuenta" es visible en la pantalla
    ○ En "Actividad de la cuenta" haga clic en <Periodo de actividad:> y seleccione "Todo"
    ○ En "Actividad de la cuenta" haga clic en <Tipo:> y seleccione "Todo"
    ○ Haga clic en <Ir>*/
    private By AccountOne = By.xpath("//a[normalize-space()='31659']");
    private By AccountTwo = By.xpath("//a[normalize-space()='32658']");

    private By TitleDetail = By.xpath("//*[@id=\"rightPanel\"]/div/div[1]/h1");

    private By ActivityPeriod = By.id("month"); // xpath //select[@id='month']
    private By AllPeriod = By.xpath("//*[@id=\"month\"]/option[1]");
    private By January = By.xpath("//*[@id=\"month\"]/option[2]");

    private By Type = By.id("transactionType"); // xpath("//select[@id='transactionType']");
    private By TypeAll = By.xpath("//*[@id=\"transactionType\"]/option[1]");

    private By BtnGo = By.xpath("//input[@value='Go']");
    private By Details = By.xpath("//*[@id=\"transactionTable\"]/tbody/tr/td[2]/a"); //Funds Transfer Sent
    private By NotFound = By.xpath("//b[normalize-space()='No transactions found.']");//"No transactions found."


    public CheckAccount(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }


}

