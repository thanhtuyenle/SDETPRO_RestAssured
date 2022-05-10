package test;

import com.google.gson.Gson;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.BuildModelJSON;
import model.PostBody;

import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
import static org.hamcrest.CoreMatchers.equalTo;

import static io.restassured.RestAssured.given;

public class PUTMethod {
    public static void main(String[] args) {
        String baseUri = "https://jsonplaceholder.typicode.com";

        RequestSpecification request = given();
        request.baseUri(baseUri);

        //content-type
        request.header(new Header("Content-type", "application/json; charset=UTF-8"));
        PostBody postBody = new PostBody();
        postBody.setUserId(1);
        postBody.setId(1);
        postBody.setTitle("This is put method's title");
        postBody.setBody("This is put method's body");


        String postBodyStr = BuildModelJSON.parseJSONString(postBody);
        final int TARGET_POST_NUM = 1;
        Response response = request.body(postBodyStr).put("/posts/".concat(String.valueOf(TARGET_POST_NUM)));
        response.prettyPrint();

        response.then().body("userId", equalTo(postBody.getUserId()));
        response.then().body("id", equalTo(postBody.getId()));
        response.then().body("title", equalTo(postBody.getTitle()));
        response.then().body("body", equalTo(postBody.getBody()));
    }
}
