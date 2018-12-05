package bot.beans;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class QueryResult {
    
    private String queryText;
    private String action;
    private Object parameters;
    private String allRequiredParamsPresent;
    private String languageCode;
    private Intent intent;
   
}
