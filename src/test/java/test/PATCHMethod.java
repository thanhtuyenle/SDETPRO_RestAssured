package test;

import com.google.gson.Gson;
import io.restassured.http.Header;
import io.restassured.mapper.ObjectMapper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.BuildModelJSON;
import model.PostBody;
import model.RequestCapability;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class PATCHMethod {
    public static void main(String[] args) {
        String baseUri = "https://jsonplaceholder.typicode.com";
        RequestSpecification request = given();
        request.baseUri(baseUri);
        request.header(RequestCapability.defaultHeader);

        final int PATCH_INDEX = 1;
        PostBody postBody = new PostBody();
        postBody.setBody("This is PATCH method's body");
        String postBodyStr = BuildModelJSON.parseJSONString(postBody);


        Response response = request.body(postBodyStr).patch("/posts/".concat(String.valueOf(PATCH_INDEX)));
        response.prettyPrint();
        response.then().body("body", equalTo(postBody.getBody()));
    }
}
