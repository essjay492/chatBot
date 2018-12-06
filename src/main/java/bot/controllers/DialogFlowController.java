package bot.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import bot.beans.DialogFlowRequest;
import bot.beans.DialogFlowResponse;
import bot.beans.FulfillmentMessages;
import bot.beans.Text;
import bot.constants.Constants;

@RestController
public class DialogFlowController {
    
    private RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private DialogFlowResponse response;
    @Autowired
    private FulfillmentMessages fulfillmentMessages;
    @Autowired
    private Text text;
    
    private ArrayList<String> arrayText = new ArrayList<String>();
    private ArrayList<FulfillmentMessages> arrayFulfillmentMessages = new ArrayList<FulfillmentMessages>();

    @ResponseBody
    @PostMapping("/sams/chatBot")
    public DialogFlowResponse rendering(@RequestBody  DialogFlowRequest request) {   
        if((request.getQueryResult()).getAction() == Constants.GET_CLUBS_ACTION) {
            String result = restTemplate.getForObject(Constants.GET_CLUBS_URI, String.class);
            arrayText.add(result);
            text.setText(arrayText);
            fulfillmentMessages.setText(text);
            arrayFulfillmentMessages.add(fulfillmentMessages);
            response.setFulfillmentText(Constants.GET_CLUBS_200);
            response.setFulfillmentMessages(arrayFulfillmentMessages);
        }
        return response;
    }
    
    @ResponseBody
    @GetMapping("/sams")
    public DialogFlowResponse greeting() {   
            String result = restTemplate.getForObject(Constants.GET_CLUBS_URI, String.class);
            arrayText.add(result);
            text.setText(arrayText);
            fulfillmentMessages.setText(text);
            arrayFulfillmentMessages.add(fulfillmentMessages);
            response.setFulfillmentText(Constants.GET_CLUBS_200);
            response.setFulfillmentMessages(arrayFulfillmentMessages);
        return response;
    }
}
