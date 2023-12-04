package Back.BackUtils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class LoginUser {

    public static String getCustomId(String userName, String password) {
        String url = BaseData.urlBase + BaseData.login + "/" + userName + "/" + password;
        Response response = RestAssured.get(url);
        if (response.statusCode() == 200) {
            System.out.println("Se ingreso a la cuenta, statusCode: " + response.statusCode());
        }else{
            System.out.println("Error durante la solicitud de inicio de sesión");
        }
        return response.path("customer.id");
    }
}
