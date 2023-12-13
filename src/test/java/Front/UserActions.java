package Front;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.CheckAccount;
import org.NewAccount;
import org.Reportes.ExtentFactory;
import org.TransFerFounds;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserActions {
    public WebDriver driver;
    private WebDriverWait wait;
    static ExtentSparkReporter info = new ExtentSparkReporter("target/REPORTES/ActionsAccount.html");
    static ExtentReports extent;

    @BeforeAll
    public static void createReports(){
        extent = ExtentFactory.getInstance();
        extent.attachReporter(info);
    }
    @BeforeEach
    public void init() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @Test
    @Tag("newaccount")
    @Tag("ALL")
    public void SavingsAccountTest() throws InterruptedException {
        ExtentTest test = extent.createTest("Test SavingsAccount Cyrus");
        test.log(Status.INFO, "Starting");
        NewAccount newAccount = new NewAccount(driver, wait);
        newAccount.setUp();
        newAccount.getUrl("https://parabank.parasoft.com/parabank/index.htm");
        test.log(Status.INFO, "Login");
        newAccount.GoToLogin("Cyrus_Suarez8", "UYA91XVW1WL");
        test.log(Status.INFO, "New account Savings");
        newAccount.newAccountSAVINGS();
        test.log(Status.INFO, "Open account");
        newAccount.openAccount();
        Boolean message = newAccount.WaitToSeeMessage(newAccount.getMessageSubt(), newAccount.getCongratulations());
        if(message){
            test.log(Status.PASS, newAccount.getCongratulations());
        }else{
            test.log(Status.FAIL, "An error occurred while opening a new account");
        }
        newAccount.close();
    }

    @Test
    @Tag("overviewAccount")
    @Tag("ALL")
    public void OverviewTest() throws InterruptedException {
        ExtentTest test = extent.createTest("Test OverviewTest Cyrus");
        test.log(Status.PASS, "Starting");
        CheckAccount account = new CheckAccount(driver, wait);
        account.setUp();
        account.getUrl("https://parabank.parasoft.com/parabank/index.htm");
        test.log(Status.PASS, "Login");
        account.GoToLogin("Cyrus_Suarez8", "UYA91XVW1WL");

        test.log(Status.PASS, "CheckAccount");
        account.initOverview();
        test.log(Status.INFO, "Open view");

        if(account.WaitToSeeMessage(account.getMessageXPath(), account.getMessage())){
            test.log(Status.PASS, "*Balance includes deposits that may be subject to holds");
        }else{
            test.log(Status.FAIL, "An error occurred while check account");
        }

        account.close();
    }

    @Test
    @Tag("detailsAccount")
    @Tag("ALL")
    public void DetailsAccountTest1() throws InterruptedException {
        ExtentTest test = extent.createTest("Test Details Account Cyrus");
        test.log(Status.INFO, "Starting");
        CheckAccount account = new CheckAccount(driver, wait);
        account.setUp();
        account.getUrl("https://parabank.parasoft.com/parabank/index.htm");
        test.log(Status.INFO, "Login");
        try{
            account.GoToLogin("Cyrus_Suarez8", "UYA91XVW1WL");
            test.log(Status.INFO, "CheckAccount");
            account.initOverview();
            test.log(Status.INFO, "Open view");

            test.log(Status.INFO, "Select Account");
            account.selectAccount("one");
            Boolean detailTitle = account.WaitToSeeMessage(account.getTitleDetailXpath(), account.getTitleDetail());
            if(detailTitle){
                test.log(Status.PASS, "Page account details");
                account.selectPeriod();
                account.selectType();
                account.finishTrans();
                String information = account.information();
                test.log(Status.INFO, information);
            }else{
                test.log(Status.FAIL, "Not found element");
            }
        }catch (Exception error) {
            test.log(Status.FAIL, error.getLocalizedMessage());
            throw error;
        }
        account.close();

    }

    @Test
    @Tag("transferFunds")
    @Tag("ALL")
    public void TransferFundsTest1() throws InterruptedException {
        ExtentTest test = extent.createTest("Test transfer funds Cyrus");
        test.log(Status.INFO, "Starting");
        TransFerFounds option1 = new TransFerFounds(driver, wait);
        option1.setUp();
        option1.getUrl("https://parabank.parasoft.com/parabank/index.htm");
        test.log(Status.INFO, "Login");
        option1.GoToLogin("Cyrus_Suarez8", "UYA91XVW1WL");

        test.log(Status.INFO, "Starting");

        option1.transferbtn();
        test.log(Status.INFO, "Wait for see the title");
        Thread.sleep(600);
        option1.WaitToSeeTheTitle(option1.getTitle());

        option1.startProcess("800");

        test.log(Status.INFO, "Select Account from");

        option1.fromAccount(2);
        test.log(Status.INFO, "Select Account to");

        option1.toAccount(1);
        option1.finishProcess();

        if(option1.WaitToSeeMessage(option1.getMessage(), "Transfer Funds")){
            test.log(Status.INFO, "successful transfer");
        }else{
            test.log(Status.FAIL, "Error while transfer funds");
        }
        option1.close();
    }

    @AfterAll
    public static void reporte(){
        extent.flush();
    }
}
