package ru.stqa.pft.rest;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.SkipException;



import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.Set;


/**
 * Created by Natusik on 25.10.2016.
 */
public class TestBase {



  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  private boolean isIssueOpen(int issueId) throws IOException {
    String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues/" + issueId + ".json"))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    Set<Issue> issuesData = new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    Issue issueStop = new Issue();
    for (Issue issue: issuesData){
      if(issue.getId() == issueId){
        issueStop = issue;
      }
    }
    if (Objects.equals(issueStop.getStatus(), "Resolved")){
      return false;
    } else {
      return true;
    }
  }

  protected Set<Issue> getIssues() throws IOException {
      String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json"))
              .returnContent().asString();
      JsonElement parsed = new JsonParser().parse(json);
      JsonElement issues = parsed.getAsJsonObject().get("issues");
      return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
  }

  private Executor getExecutor() {
      return Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==", "");
  }

  protected int createIssue(Issue newIssue) throws IOException {
      String json = getExecutor().execute(Request.Post("http://demo.bugify.com/api/issues.json")
              .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                          new BasicNameValuePair("description", newIssue.getDescription()),
                      new BasicNameValuePair("state_name", newIssue.getDescription())))
              .returnContent().asString();
      JsonElement parsed = new JsonParser().parse(json);
      return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }
}
