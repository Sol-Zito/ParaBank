package Front;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.NewAccount;
import org.Reportes.ExtentFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ActionsAccount {
    public WebDriver driver;
    private WebDriverWait wait;
    static ExtentSparkReporter info = new ExtentSparkReporter("target/REPORTES/AcctionsAccount.html");
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
    public void testRegister() throws InterruptedException {
        ExtentTest test = extent.createTest("Test register Cyrus");
        test.log(Status.INFO, "Starting");
        NewAccount newAccount = new NewAccount(driver, wait);
        newAccount.setUp();
        newAccount.getUrl("https://parabank.parasoft.com/parabank/index.htm");
        test.log(Status.INFO, "Login");
        newAccount.GoToLogin("CyrusSuarez2", "UYA91XVW1WL");

        test.log(Status.INFO, "New account Savings");
        newAccount.newAccountSAVINGS();
        test.log(Status.INFO, "Open account");

        String message = newAccount.openAccount();

        if(message.contains("Congratulations")){
            test.log(Status.PASS, "Congratulations, your account is now open.");
        }else{
            test.log(Status.FAIL, "An error occurred while opening a new account");
        }

        newAccount.close();
    }


    @AfterAll
    public static void reporte(){
        extent.flush();
    }
}
