package bot.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DialogFlowRequest {
    
    @Autowired
    private QueryResult queryResult;
    private String responseId;
    private String session;
    
    public String getResponseId() {
        return responseId;
    }
    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }
    public QueryResult getQueryResult() {
        return queryResult;
    }
    public void setQueryResult(QueryResult queryResult) {
        this.queryResult = queryResult;
    }
    public String getSession() {
        return session;
    }
    public void setSession(String session) {
        this.session = session;
    } 
 
}
