package test;

import model.RequestCapability;

import utils.ProjectInfo;

public class JiraIssueTypes implements RequestCapability {
    public static void main(String[] args) {
        String baseUri = "https://tuyenthanhle.atlassian.net";
        String projectKey = "SRAT";

        ProjectInfo projectInfo = new ProjectInfo(baseUri, projectKey);
        String issueTypeId = projectInfo.getIssueTypeId("Task");
        System.out.println("Task ID: " + issueTypeId);
    }
}
