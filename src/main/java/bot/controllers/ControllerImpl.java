package bot.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Component;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import com.google.gson.Gson;

import bot.beans.DialogFlowRequest;
import bot.beans.DialogFlowResponse;
import bot.beans.FulfillmentMessages;
import bot.beans.Text;
import bot.constants.Constants;
import log.Logging;

@Component

public class ControllerImpl {

    @Autowired
    private DialogFlowResponse response;
    @Autowired
    private FulfillmentMessages fulfillmentMessages;
    @Autowired
    private Text text;

    private ArrayList<String> arrayText = new ArrayList<String>();
    private ArrayList<FulfillmentMessages> arrayFulfillmentMessages = new ArrayList<FulfillmentMessages>();
    private Gson gson = new Gson();
    private RestTemplate restTemplate = new RestTemplate();

    @HystrixCommand(fallbackMethod = "reliable", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = Constants.TIMEOUT)
         })
    public DialogFlowResponse getClubs(DialogFlowRequest request) {
        Logging.requestSentToBackend(gson.toJson(request), Constants.GET_CLUBS_URI, Constants.GET);
        String result = restTemplate.getForObject(Constants.GET_CLUBS_URI, String.class);
        Logging.responseFromBackend(result, Constants.GET_CLUBS_URI);
        arrayText.add(result);
        text.setText(arrayText);
        fulfillmentMessages.setText(text);
        arrayFulfillmentMessages.add(fulfillmentMessages);
        response.setFulfillmentText(Constants.GET_CLUBS_200);
        response.setFulfillmentMessages(arrayFulfillmentMessages);
        return response;
    }
    
    public DialogFlowResponse defaultAction() {
        Logging.defaultAction();
        arrayText.add(Constants.DEFAULT_ACTION);
        text.setText(arrayText);
        fulfillmentMessages.setText(text);
        arrayFulfillmentMessages.add(fulfillmentMessages);
        response.setFulfillmentText(Constants.DEFAULT_ACTION);
        response.setFulfillmentMessages(arrayFulfillmentMessages);
        return response;
    }
    
    public DialogFlowResponse reliable(DialogFlowRequest request) {
        Logging.circuitBreaker();
        arrayText.add(Constants.ERROR_500);
        text.setText(arrayText);
        fulfillmentMessages.setText(text);
        arrayFulfillmentMessages.add(fulfillmentMessages);
        response.setFulfillmentText(Constants.ERROR_500);
        response.setFulfillmentMessages(arrayFulfillmentMessages);
        return response;
      }
    
    
}
