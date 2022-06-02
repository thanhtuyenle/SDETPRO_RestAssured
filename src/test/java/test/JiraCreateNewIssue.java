package test;


import com.google.gson.Gson;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.IssueFields;
import model.RequestCapability;
import utils.AuthenticationHandler;
import utils.ProjectInfo;

import static io.restassured.RestAssured.given;

public class JiraCreateNewIssue implements RequestCapability {
    public static void main(String[] args) {
        //API Info
        String baseUri = "https://tuyenthanhle.atlassian.net";
        String path = "/rest/api/3/issue";
        String projectKey = "SRAT";

        //Request Object
        String email = System.getenv("email");
        String apiToken = System.getenv("token");
        String encodeCredStr = AuthenticationHandler.encodeCredStr(email, apiToken);

        RequestSpecification request = given();
        request.baseUri(baseUri);
        request.header(defaultHeader);
        request.header(getAuthenticatedHeader.apply(encodeCredStr));
        request.header(acceptJsonHeader);

        ProjectInfo projectInfo = new ProjectInfo(baseUri, projectKey);
        String taskId = projectInfo.getIssueTypeId("Task");
        String summary = "This is my summary || API create issue";
        IssueFields.Project project = new IssueFields.Project(projectKey);
        IssueFields.IssueType issuetype = new IssueFields.IssueType(taskId);
        IssueFields.Fields fields = new IssueFields.Fields(summary, project, issuetype);
        IssueFields issueFields = new IssueFields(fields);

        System.out.println("test -------" + issueFields);

        Response response = request.body(new Gson().toJson(issueFields)).post(path);
        response.prettyPrint();
    }
}
