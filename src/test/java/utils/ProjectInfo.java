package utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.RequestCapability;
import org.apache.commons.codec.binary.Base64;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ProjectInfo implements RequestCapability {
    private String baseUri;
    private String projectKey;

    private List<Map<String, String>> issueTypes;
    Map<String, List<Map<String, String>>> projectInfo;

    public ProjectInfo(String baseUri, String projectKey) {
        this.baseUri = baseUri;
        this.projectKey = projectKey;
        getProjectInfo();
    }

    public String getIssueTypeId (String issueTypeStr) {
        getIssueTypes();
        String issueTypeId = null;
        for (Map<String, String> issueType : issueTypes) {
            if(issueType.get("name").equalsIgnoreCase(issueTypeStr)) {
                issueTypeId = issueType.get("id");
                break;
            }
        }
        if (issueTypeId == null)
            throw new IllegalArgumentException("[ERR] could not find issueTypeId = " + issueTypeStr);
        return issueTypeId;
    }

    private void getIssueTypes() {
        issueTypes = projectInfo.get("issueTypes");
    }

    private void getProjectInfo() {
        //String baseUri = "https://tuyenthanhle.atlassian.net";
        String path = "/rest/api/3/project/".concat(projectKey);

        String email = System.getenv("email");
        String apiToken = System.getenv("token");
        String credential = email.concat(":").concat(apiToken);

        byte[] encodeCredential = Base64.encodeBase64(credential.getBytes());
        String encodeCredStr = new String(encodeCredential);

        RequestSpecification request = given();
        request.baseUri(baseUri);
        request.header(defaultHeader);
        //request.header(RequestCapability.getAuthenticatedHeader(encodeCredStr)); //get authenticated header from static method
        request.header(getAuthenticatedHeader.apply(encodeCredStr)); //using Java 8 feature lambda expression

        Response response = request.get(path);
        response.prettyPrint();

        projectInfo = JsonPath.from(response.asString()).get();
    }
}
