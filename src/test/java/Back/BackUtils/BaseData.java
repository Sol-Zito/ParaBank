package Back.BackUtils;

public class BaseData {
    public static String urlBase = "https://parabank.parasoft.com/parabank/services/bank";
    public String createAccount = "/createAccount/?customerId=";
    public static String login = "/login";
    public String customers = "/customers";
    public String accounts = "/accounts";
    public String overview = "/overview";
    public String transfer = "/transfer";

    public String getUrlBase() {
        return urlBase;
    }

    public String getCreateAccount() {
        return createAccount;
    }

    public String getCustomers() {
        return customers;
    }

    public String getAccounts() {
        return accounts;
    }
    public String getLogin() {
        return login;
    }
}
