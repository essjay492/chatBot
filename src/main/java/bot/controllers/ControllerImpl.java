package bot.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import bot.beans.dialogFlow.DialogFlowRequest;
import bot.beans.dialogFlow.DialogFlowResponse;
import bot.beans.dialogFlow.FulfillmentMessages;
import bot.beans.dialogFlow.Text;
import bot.beans.mobileProxy.Item;
import bot.beans.mobileProxy.OrderDetails;
import bot.beans.mobileProxy.OrderMap;
import bot.beans.mobileProxy.User;
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
    @Autowired
    private User user;
    @Autowired
    private OrderMap orderMap;
    @Autowired
    private OrderDetails orderDetails;

    private ArrayList<String> arrayText = new ArrayList<String>();
    private ArrayList<FulfillmentMessages> arrayFulfillmentMessages = new ArrayList<FulfillmentMessages>();
    private Gson gson = new Gson();
    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers = new HttpHeaders();

    final private static String email = "jo@scan.com";
    final private static String password = "12345678";

    @HystrixCommand(fallbackMethod = "reliable", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = Constants.TIMEOUT) })
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

    public DialogFlowResponse orderDetails(DialogFlowRequest request) {
        Logging.requestSentToBackend(gson.toJson(request), Constants.ORDER_DETAILS_URI, Constants.POST);
        addBasicRequestHeaderFields();
        login();
        addAuthRequestHeaderFields();
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(user, headers);
        orderMap = restTemplate.postForObject(Constants.ORDER_LIST_URI, requestEntity, OrderMap.class);
        user.setOrderId(orderMap.getOrderMap().get(0).getId());
        requestEntity = new HttpEntity<Object>(user, headers);
        orderDetails = restTemplate.postForObject(Constants.ORDER_DETAILS_URI, requestEntity,OrderDetails.class);
        Map<String,Integer> itemMapping = new HashMap<String,Integer>();
        for (Item item: orderDetails.getNotShippedItems()) {
            if (itemMapping.containsKey(item.getState())) {
                Integer itemCount = itemMapping.get(item.getState()) + 1;
                itemMapping.put(item.getState(), itemCount);
            } else {
                itemMapping.put(item.getState(), 1);
            }
        }
        StringBuilder sendText = new StringBuilder("As per our records, you ordered ");
        for (Entry<String, Integer> entry : itemMapping.entrySet()) {
            sendText.append(entry.getValue() +" items " + "having status " + entry.getKey());
        }
        arrayText.add(sendText.toString());
        text.setText(arrayText);
        fulfillmentMessages.setText(text);
        arrayFulfillmentMessages.add(fulfillmentMessages);
        response.setFulfillmentText(sendText.toString());
        response.setFulfillmentMessages(arrayFulfillmentMessages);
        return response;
    }

    public User login() {
        user.setEmail(email);
        user.setPassword(password);
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(user, headers);
        user = restTemplate.postForObject(Constants.LOGIN_URI, requestEntity, User.class);
        return user;
    }
    
    private void addBasicRequestHeaderFields() {
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "application/json");
    }
    
    private void addAuthRequestHeaderFields() {
        headers.add("Control", "a56b3dfb1f4aed4311ce966155a8ab30690e36b9259a9e108d20ba1ddb1d04bd");
        headers.add("requestID", "BAF0E1B0-3F84-4373-B700-22AF735E7FE3");
        headers.add("timestamp", "1530599204357");
        headers.add("Authorization", "Bearer " + user.getAuthToken());
        headers.add("JSESSIONATG", user.getToken());
        headers.add("email", email); 
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
