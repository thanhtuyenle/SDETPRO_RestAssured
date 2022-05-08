package test;

import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class SimpleTest {
    public static void main(String[] args) {
        String baseUrl = "https://jsonplaceholder.typicode.com";

        //request scope
        RequestSpecification request = given();
        request.baseUri(baseUrl);
        request.basePath("/todos");

        //response scope
        final String FIRST_TODO = "/1";
        Response response = request.get(FIRST_TODO); //get first todos
        response.prettyPrint();
        response.then().body("userId", equalTo(1));
        response.then().body("id", equalTo(1));
        response.then().body("title", equalTo("delectus aut autem"));
        response.then().body("completed", equalTo(false));
    }
}
