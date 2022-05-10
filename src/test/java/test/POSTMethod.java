package test;

import com.google.gson.Gson;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.PostBody;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
import static org.hamcrest.CoreMatchers.equalTo;

import static io.restassured.RestAssured.given;

public class POSTMethod {
    public static void main(String[] args) {
        String baseUri = "https://jsonplaceholder.typicode.com";

        RequestSpecification request = given();
        request.baseUri(baseUri);

        //content-type
        request.header(new Header("Content-type", "application/json; charset=UTF-8"));

        PostBody postBody1 = new PostBody(1, 1, "Title 1", "Body 1");
        PostBody postBody2 = new PostBody(1, 1, "Title 2", "Body 2");
        PostBody postBody3 = new PostBody(1, 1, "Title 3", "Body 3");

        List<PostBody> postBodyList = Arrays.asList(postBody1, postBody2, postBody3);
        for(PostBody postBody: postBodyList) {
            System.out.println(postBody.toString());

            Gson gson = new Gson();
            //send POST method
            Response response = request.body(gson.toJson(postBody)).post("/posts");
            response.prettyPrint();
            System.out.println(response.statusCode());
            System.out.println(response.statusLine());

            //verify response
            response.then().statusCode(201);
            response.then().statusLine(containsStringIgnoringCase("201 Created"));
            //response.then().body("id", equalTo(postBody.getId()));
            response.then().body("userId", equalTo(postBody.getUserId()));
            response.then().body("title", equalTo(postBody.getTitle()));
            response.then().body("body", equalTo(postBody.getBody()));
        }

        Gson gson = new Gson();
        PostBody postBody = new PostBody();
        postBody.setUserId(1);
        postBody.setId(1);
        postBody.setTitle("This is request's title");
        postBody.setBody("This is request's body");

        //send POST method
        Response response = request.body(gson.toJson(postBody)).post("/posts");
        response.prettyPrint();

        System.out.println(response.statusCode());
        System.out.println(response.statusLine());

        //verify response
        response.then().statusCode(201);
        response.then().statusLine(containsStringIgnoringCase("201 Created"));
        response.then().body("userId", equalTo(1));
        response.then().body("title", equalTo("This is request's title"));
        response.then().body("body", equalTo("This is request's body"));

    }
}
