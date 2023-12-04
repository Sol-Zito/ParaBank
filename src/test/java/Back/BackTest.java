package Back;

import Back.BackUtils.LoginUser;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import Back.BackUtils.BaseData;
import org.Reportes.ExtentFactory;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;


public class BackTest {
    static ExtentSparkReporter info = new ExtentSparkReporter("target/REPORTES/ActionsUserBack.html");
    static ExtentReports extent;
    String userName = "Cyrus_Suarez8";
    String password = "UYA91XVW1WL";
    BaseData data = new BaseData();
    String customerId;

    @BeforeAll
    public static void createReports(){
        extent = ExtentFactory.getInstance();
        extent.attachReporter(info);
    }

    @BeforeEach
    public void forEachLogUser(){
        customerId = LoginUser.getCustomId(userName,password);

    }

    /*Abrir una nueva cuenta URL:
    https://parabank.parasoft.com/parabank/services_proxy/bank/createAccount?customerId=12545&newAccountType=1&fromAccountId=xxxxx*/

    @Test
    @Tag("createAccount")
    public void createAccount() {
        ExtentTest test = extent.createTest("Test createAccount");
        test.log(Status.INFO, "Starting");

        String accountsUrl = BaseData.urlBase + data.customers + "/" + customerId + data.accounts;

        Response response = RestAssured.get(accountsUrl);
        Response accountsResponse;

        if (response.statusCode() == 200) {
            accountsResponse = response;
            test.log(Status.INFO,"Se obtuvieron cuentas del usuario");

            String fromAccountId =  accountsResponse.path("accounts.account[0].id");
            String fromAccountId1 =  accountsResponse.path("accounts.account[1].id");

            given().contentType("application/json")
                    .when().post(BaseData.urlBase + data.createAccount + customerId + "&newAccountType="+1+"&fromAccountId="+ fromAccountId1)
                    .then().statusCode(200)
                    .log().status()
                    .log().body();
        }else{
            test.log(Status.FAIL,"Error al obtener cuentas del usuario");
        }

    }

    /*Descarga de fondos URL:
    https://parabank.parasoft.com/parabank/services_proxy/bank/transfer?fromAccountId=13566&toAccountId=13677&amount=xxxxx*/
    @Test
    @Tag("TransferFunds")
    public void TransferFunds () {
        ExtentTest test = extent.createTest("Test TransferFunds");
        test.log(Status.INFO, "Starting");

        String accountsUrl = BaseData.urlBase + data.customers + "/" + customerId + data.accounts;

        Response response = RestAssured.get(accountsUrl);
        Response accountsResponse;
        if (response.statusCode() == 200) {
            accountsResponse = response;
            test.log(Status.INFO,"User accounts were obtained");

            String fromAccountId1 =  accountsResponse.path("accounts.account[1].id");
            String toAccountId2 =  accountsResponse.path("accounts.account[2].id");

            given().contentType("application/json")
                    .when().post(BaseData.urlBase + data.transfer + "?fromAccountId="+ fromAccountId1 + "&toAccountId="+ toAccountId2 + "&amount=" + 400)
                    .then().statusCode(200)
                    .log().body();
        }else{
            test.log(Status.FAIL,"Error transferring funds");
        }
    }

    /*Actividad de la cuenta (cada mes) URL:
    https://parabank.parasoft.com/parabank/services_proxy/bank/accounts/13566/transactions/month/All/type/All*/

    @Test
    @Tag("TransactionsByMonth")
    public void TransactionsByMonth () {
        ExtentTest test = extent.createTest("Test TransactionsByMonth");
        test.log(Status.INFO, "Starting");

        String accountsUrl = BaseData.urlBase + data.customers + "/" + customerId + data.accounts;

        Response response = RestAssured.get(accountsUrl);
        Response accountsResponse;
        if (response.statusCode() == 200) {
            accountsResponse = response;
            test.log(Status.INFO,"User accounts were obtained");
            String accountId =  accountsResponse.path("accounts.account[1].id");
            test.log(Status.PASS, "Searching for transactions from all months of the account: " + accountId);

            String datos = given().contentType("application/json")
                    .when().get(BaseData.urlBase + data.accounts + "/" + accountId + "/transactions/month/All/type/All")
                    .then().statusCode(200)
                    .log().body().toString();

            test.log(Status.INFO, datos);

        }else{
            test.log(Status.FAIL,"Error getting transaction information");
        }
    }



    @AfterAll
    public static void reporte(){
        extent.flush();
    }
}
