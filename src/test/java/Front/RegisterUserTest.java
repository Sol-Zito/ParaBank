package Front;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.RegisterUser;
import org.Reportes.ExtentFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterUserTest {

    public WebDriver driver;
    private WebDriverWait wait;
    static ExtentSparkReporter info = new ExtentSparkReporter("target/REPORTES/RegisterUser.html");
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
        RegisterUser registerUser = new RegisterUser(driver, wait);
        registerUser.setUp();
        registerUser.getUrl("https://parabank.parasoft.com/parabank/index.htm");
    }

    @Test
    @Tag("register")
    @Tag("ALL")
    public void testRegister1() throws InterruptedException {
        ExtentTest test = extent.createTest("Test register Cyrus");
        test.log(Status.INFO, "Starting");

        RegisterUser Cyrus = new RegisterUser(driver, wait);

        Cyrus.RegisterUser();
        test.log(Status.PASS, "Complete with registration");
        Cyrus.writeName("Cyrus");
        Cyrus.writeLastName("Suarez");
        Cyrus.writeAddress("Ap #658-9330 Varius Street","Darwin", "FATA", "28826");
        Cyrus.writePhone("1-237-446-3353");
        Cyrus.writeSsn("H0I 4V7");

        test.log(Status.PASS, "Complete user and password");
        Cyrus.writeUserName("CyrusSuarez2");
        Cyrus.writePassWordAndRePassWord("UYA91XVW1WL", "UYA91XVW1WL");


        test.log(Status.PASS, "Check in");
        Cyrus.CheckIn();

        test.log(Status.INFO, "Wait for registration");
        if(Cyrus.WaitForMessage()){
            test.log(Status.PASS, "Your account was created successfully. You are now logged in.");
        }else {test.log(Status.FAIL, "An error occurred while generating user");}

    }

    @Test
    @Tag("register")
    @Tag("ALL")
    public void testRegister2() throws InterruptedException {
        ExtentTest test = extent.createTest("Test register Dylan");
        test.log(Status.INFO, "Starting");

        RegisterUser Dylan = new RegisterUser(driver, wait);
        Dylan.RegisterUser();

        test.log(Status.PASS, "Complete with registration");
        Dylan.writeName("Dylan");
        Dylan.writeLastName("Duncan");
        Dylan.writePhone("416-7611");
        Dylan.writeAddress("8156 Fusce Road","Apartadó","Haute-Normandie","23882");
        Dylan.writeSsn("1-625-477-5628");

        test.log(Status.PASS, "Complete user and password");
        Dylan.writeUserName("Dylan");
        Dylan.writePassWordAndRePassWord("QGM09YVW9JS","QGM09YVW9JS");


        test.log(Status.PASS, "Check in");
        Dylan.CheckIn();

        test.log(Status.INFO, "Wait for registration");
        if(Dylan.WaitForMessage()){
            test.log(Status.PASS, "Your account was created successfully. You are now logged in.");
        }else {test.log(Status.FAIL, "An error occurred while generating user");}


    }

    @AfterEach
    public void quit() {
        RegisterUser registerUser = new RegisterUser(driver, wait);
        registerUser.close();
    }

    @AfterAll
    public static void reporte(){
        extent.flush();
    }

}
