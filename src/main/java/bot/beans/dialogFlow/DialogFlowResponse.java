package bot.beans.dialogFlow;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DialogFlowResponse {
    
    @Autowired
    private List<FulfillmentMessages> fulfillmentMessages;
    private String fulfillmentText;
//    private String source;
//    private OutputContexts outputContexts;
//    private FollowupEventInput followupEventInput;
    public String getFulfillmentText() {
        return fulfillmentText;
    }
    public void setFulfillmentText(String fulfillmentText) {
        this.fulfillmentText = fulfillmentText;
    }
    public List<FulfillmentMessages> getFulfillmentMessages() {
        return fulfillmentMessages;
    }
    public void setFulfillmentMessages(List<FulfillmentMessages> fulfillmentMessages) {
        this.fulfillmentMessages = fulfillmentMessages;
    }

}
