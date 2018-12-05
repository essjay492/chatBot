package bot.beans;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class DialogFlowRequest {
    
    private String responseId;
    private QueryResult queryResult;
    private String session; 
 
}
