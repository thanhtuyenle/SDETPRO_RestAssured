package test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.RequestCapability;
import org.apache.commons.codec.binary.Base64;


import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;

public class JiraIssueTypes implements RequestCapability {
    public static void main(String[] args) {
        String baseUri = "https://tuyenthanhle.atlassian.net";
        String path = "/rest/api/3/project/SRAT";

        String email = System.getenv("email");
        String apiToken = System.getenv("token");
        String credential = email.concat(":").concat(apiToken);

        byte[] encodeCredential = Base64.encodeBase64(credential.getBytes());
        String encodeCredStr = new String(encodeCredential);

        RequestSpecification request = given();
        request.baseUri(baseUri);
        request.header(defaultHeader);
        request.header("Authorization", "Basic ".concat(encodeCredStr));


        Response response = request.get(path);
        response.prettyPrint();

    }
}
