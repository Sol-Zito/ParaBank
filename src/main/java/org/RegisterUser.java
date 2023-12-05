package org;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterUser extends BasePage {

    private By BtnRegister = By.xpath("//a[normalize-space()='Register']");

    private By FirstName = By.id("customer.firstName");
    private By LastName = By.id("customer.lastName");
    private By Address = By.id("customer.address.street");
    private By City = By.id("customer.address.city");
    private By State = By.id("customer.address.state");
    private By ZipCode = By.id("customer.address.zipCode");
    private By Phone = By.id("customer.phoneNumber");
    private By SSN = By.id("customer.ssn");


    private By UserName = By.id("customer.username");
    private By Password = By.id("customer.password");
    private By RePassword = By.id("repeatedPassword");

    private By BtnConfirmation = By.xpath("//input[@value='Register']");

    private By MessageSubtitle = By.xpath("//*[@id=\"rightPanel\"]/p");
    private String Message= "Your account was created successfully. You are now logged in.";

    public RegisterUser(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void RegisterUser() throws InterruptedException {
        click(BtnRegister);
    }

    public void writeName(String name) throws InterruptedException {
        sendText(name, FirstName);
    }

    public void writeLastName(String lastname) throws InterruptedException {
        sendText(lastname, LastName);
    }

    public void writeAddress(String address, String city, String state, String zipCode) throws InterruptedException {
        sendText(address, Address);
        sendText(city, City);
        sendText(state, State);
        sendText(zipCode, ZipCode);
    }

    public void writeSsn(String ssn) throws InterruptedException {
        sendText(ssn,SSN);
    }

    public void writePhone(String phone) throws InterruptedException {
        sendText(phone, Phone);
    }

    public void writeUserName(String username) throws InterruptedException {
        sendText(username, UserName);
    }

    public void writePassWordAndRePassWord(String pass, String resPass) throws InterruptedException {
        sendText(pass, Password);
        sendText(resPass, RePassword);
    }

    public void CheckIn() throws InterruptedException {
        click(BtnConfirmation);
    }

    public By getMessageSubtitle() {
        return MessageSubtitle;
    }

    public String getMessage() {
        return Message;
    }
}
