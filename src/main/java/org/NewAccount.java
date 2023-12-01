package org;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewAccount extends BasePage {
    /*Abrir una nueva cuenta
    ○ Haga clic en <Abrir nueva cuenta>.
    ○ En el apartado "¿Qué tipo de cuenta desea abrir?" seleccione la opción <SAVINGS>.
    ○ Haga clic en <Abrir nueva cuenta>
    ○ Compruebe si el texto "Congratulations, your account is now open." está visible en
    la pantalla*/

    private By NewAccount = By.xpath("//a[normalize-space()='Open New Account']");

    private By AccountType = By.xpath("//select[@id='type']");
    private By SAVINGS = By.xpath("//*[@id=\"type\"]/option[2]");

    private By OpenAccount = By.xpath("//input[@value='Open New Account']");
    private By MessageSubt = By.xpath("//*[@id=\"rightPanel\"]/div/div/p[1]");
    String Congratulations = "Congratulations, your account is now open.";

    public NewAccount(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void newAccountSAVINGS() throws InterruptedException {
        click(NewAccount);
        click(AccountType);
        wait.until(ExpectedConditions.elementToBeClickable(SAVINGS)).click();
    }

    public String openAccount() throws InterruptedException {
        click(OpenAccount);
        if(getText(MessageSubt).isBlank()){
            return "An error occurred while opening a new account";
        }else {
            return getText(MessageSubt);
        }
    }


}
