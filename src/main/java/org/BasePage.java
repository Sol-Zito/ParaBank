package org;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {
    private By UserName = By.name("username");
    private By Password = By.name("password");
    private By Login = By.xpath("//input[@value='Log In']");

    private By Overview = By.xpath("//a[normalize-space()='Accounts Overview']");

    private static WebDriver driver;
    static WebDriverWait wait;

    public BasePage(WebDriver driver, WebDriverWait wait) {
        BasePage.driver = driver;
        BasePage.wait = wait;
    }

    public void setUp() {
        driver.manage().window().maximize();
    }

    public void getUrl(String url) {
        driver.get(url);
    }

    public void close() {
        driver.quit();
    }

    protected WebElement elementFind(By locator) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return driver.findElement(locator);
    }

    protected void sendText(String inputText, By locator) throws InterruptedException {
        this.elementFind(locator).clear();
        this.elementFind(locator).sendKeys(inputText);
    }

    protected void sendKey(CharSequence key, By locator) throws InterruptedException {
        this.elementFind(locator).sendKeys(key);
    }

    protected void click(By locator) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        this.elementFind(locator).click();
    }

    protected String getText(By locator) throws InterruptedException {
        return this.elementFind(locator).getText();
    }

    public void GoToLogin(String userName, String password) throws InterruptedException {
        sendText(userName, UserName);
        sendText(password, Password);
        click(Login);
    }

    public void initOverview() throws InterruptedException {
        click(Overview);
    }

    public Boolean WaitForMessage(By locator, String message) throws InterruptedException {
        if( wait.until(ExpectedConditions.textToBe(locator, message)) ){
            return true;
        }
        return false;
    }

    public String WaitForTitle(By locator) throws InterruptedException {
       if (elementFind(locator).getText() != null){
           return getText(locator);
       }else {
           return "Not found element";
       }
    }


}