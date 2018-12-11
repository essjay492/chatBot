package bot.beans.dialogFlow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueryResult {
    
    @Autowired
    private Intent intent;
    private String queryText;
    private String action;
    @Autowired
    private Parameters parameters;
    private String allRequiredParamsPresent;
    private String languageCode;
    
    public String getQueryText() {
        return queryText;
    }
    public void setQueryText(String queryText) {
        this.queryText = queryText;
    }
    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }
    
    public Parameters getParameters() {
        return parameters;
    }
    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }
    public String getAllRequiredParamsPresent() {
        return allRequiredParamsPresent;
    }
    public void setAllRequiredParamsPresent(String allRequiredParamsPresent) {
        this.allRequiredParamsPresent = allRequiredParamsPresent;
    }
    public String getLanguageCode() {
        return languageCode;
    }
    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }
    public Intent getIntent() {
        return intent;
    }
    public void setIntent(Intent intent) {
        this.intent = intent;
    }
   
}
