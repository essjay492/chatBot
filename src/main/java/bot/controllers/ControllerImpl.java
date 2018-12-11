package bot.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import bot.beans.dialogFlow.Attributes;
import bot.beans.dialogFlow.DialogFlowRequest;
import bot.beans.dialogFlow.DialogFlowResponse;
import bot.beans.dialogFlow.FulfillmentMessages;
import bot.beans.dialogFlow.ItemRecords;
import bot.beans.dialogFlow.Records;
import bot.beans.dialogFlow.SearchRequest;
import bot.beans.dialogFlow.SearchResponse;
import bot.beans.dialogFlow.Text;
import bot.beans.mobileProxy.Item;
import bot.beans.mobileProxy.OrderDetails;
import bot.beans.mobileProxy.OrderMap;
import bot.beans.mobileProxy.User;
import bot.beans.dialogFlow.MainArea;
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
    @Autowired
    private SearchResponse searchResponse;
    @Autowired
    private Attributes attributes;

    private Gson gson = new Gson();
    private List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
    private MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

    final private static String email = "gopa@walmart.com";
    final private static String password = "12345678";
    final private static String maxResults = "20";
    final private static String sort = "0";
    final private static String startOffSet = "0";

    private void addBasicRequestHeaderFields(HttpHeaders headers) {
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    }

    private void addAuthRequestHeaderFields(HttpHeaders headers) {
        headers.add("Control", "a56b3dfb1f4aed4311ce966155a8ab30690e36b9259a9e108d20ba1ddb1d04bd");
        headers.add("requestID", "BAF0E1B0-3F84-4373-B700-22AF735E7FE3");
        headers.add("timestamp", "1530599204357");
        headers.add("Authorization", "Bearer " + user.getAccessTokenVO().getAccessToken());
        headers.add("JSESSIONATG", user.getToken());
        // headers.add("email", email);
    }

    @HystrixCommand(fallbackMethod = "reliable", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = Constants.TIMEOUT) })
    public DialogFlowResponse getClubs(DialogFlowRequest request) {
        Logging.requestSentToBackend(gson.toJson(request), Constants.GET_CLUBS_URI, Constants.GET);
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(Constants.GET_CLUBS_URI, String.class);
        Logging.responseFromBackend(result, Constants.GET_CLUBS_URI);
        ArrayList<String> arrayText = new ArrayList<String>();
        ArrayList<FulfillmentMessages> arrayFulfillmentMessages = new ArrayList<FulfillmentMessages>();
        arrayText.add("Too many clubs, can't list them all!");
        text.setText(arrayText);
        fulfillmentMessages.setText(text);
        arrayFulfillmentMessages.add(fulfillmentMessages);
        response.setFulfillmentText("Too many clubs, can't list them all!");
        response.setFulfillmentMessages(arrayFulfillmentMessages);
        return response;
    }

    public DialogFlowResponse defaultAction() {
        Logging.defaultAction();
        ArrayList<String> arrayText = new ArrayList<String>();
        ArrayList<FulfillmentMessages> arrayFulfillmentMessages = new ArrayList<FulfillmentMessages>();
        arrayText.add(Constants.DEFAULT_ACTION);
        text.setText(arrayText);
        fulfillmentMessages.setText(text);
        arrayFulfillmentMessages.add(fulfillmentMessages);
        response.setFulfillmentText(Constants.DEFAULT_ACTION);
        response.setFulfillmentMessages(arrayFulfillmentMessages);
        return response;
    }

    public DialogFlowResponse searchItem(DialogFlowRequest request) {
        Logging.requestSentToBackend(gson.toJson(request), Constants.SEARCH_URL, Constants.POST);
        System.out.println(request);
        HttpEntity<Object> requestEntity = null;

        RestTemplate restTemplate = new RestTemplate();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);

        HttpHeaders headers = new HttpHeaders();
        addBasicRequestHeaderFields(headers);

        // calling login
        requestEntity = new HttpEntity<Object>(user, headers);
        user = restTemplate.postForObject(Constants.LOGIN_URI, requestEntity, User.class);
        Logging.responseFromBackend(gson.toJson(user), Constants.LOGIN_URI);
        addAuthRequestHeaderFields(headers);

        SearchRequest searchRequest = new SearchRequest();
        String item = request.getQueryResult().getParameters().getItem();
        if (item != null) {
            searchRequest.setText(item);
        } else {
            return defaultAction();
        }
        searchRequest.setStoreId(Constants.DEFAULT_STORE_ID);
        searchRequest.setMaxResults(maxResults);
        searchRequest.setSort(sort);
        searchRequest.setStartOffSet(startOffSet);

        requestEntity = new HttpEntity<Object>(searchRequest, headers);
        searchResponse = restTemplate.postForObject(Constants.SEARCH_URL, requestEntity, SearchResponse.class);

        StringBuilder sendText = new StringBuilder();

        
        if (searchResponse != null) {
            for(MainArea mainArea : searchResponse.getMainArea()) {
                if (mainArea != null) {
                    for(Records records : mainArea.getRecords()) {
                        if (records != null) {
                            attributes = records.getAttributes();
                            if (attributes != null) {
                                String price = attributes.getSkuFinalPrice().get(0);
                                String name = attributes.getProductDisplayText().get(0);
                                if(price != null && name != null) {
                                    sendText.append("ITEM NAME : "+name + " - PRICE : "+price+"\n");
                                }
                            }
                        }
                    }
                }
            }
        }
        
        if(sendText.toString().equals("")) {
            sendText.append("Sorry, could not find any results");
        }
        
        ArrayList<String> arrayText = new ArrayList<String>();
        ArrayList<FulfillmentMessages> arrayFulfillmentMessages = new ArrayList<FulfillmentMessages>();
        arrayText.add(sendText.toString());
        text.setText(arrayText);
        fulfillmentMessages.setText(text);
        arrayFulfillmentMessages.add(fulfillmentMessages);
        response.setFulfillmentText(sendText.toString());
        response.setFulfillmentMessages(arrayFulfillmentMessages);
        return response;
    }

    @HystrixCommand(fallbackMethod = "reliable", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = Constants.TIMEOUT) })
    public DialogFlowResponse orderDetails(DialogFlowRequest request) {

        Logging.requestSentToBackend(gson.toJson(request), Constants.ORDER_DETAILS_URI, Constants.POST);

        HttpEntity<Object> requestEntity = null;

        RestTemplate restTemplate = new RestTemplate();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);

        HttpHeaders headers = new HttpHeaders();
        addBasicRequestHeaderFields(headers);

        // calling login
        requestEntity = new HttpEntity<Object>(user, headers);
        user = restTemplate.postForObject(Constants.LOGIN_URI, requestEntity, User.class);
        Logging.responseFromBackend(gson.toJson(user), Constants.LOGIN_URI);
        addAuthRequestHeaderFields(headers);

        // calling customOrderLookUp
        requestEntity = new HttpEntity<Object>(user, headers);
        orderMap = restTemplate.postForObject(Constants.ORDER_LIST_URI, requestEntity, OrderMap.class);
        Logging.responseFromBackend(gson.toJson(orderMap), Constants.ORDER_LIST_URI);

        StringBuilder sendText = new StringBuilder("As per the records, ");
        if (!orderMap.getOrderMap().isEmpty()) {
            user.setOrderId(orderMap.getOrderMap().get(0).getId());

            // calling getOrderDetails
            orderDetails = restTemplate.postForObject(Constants.ORDER_DETAILS_URI, requestEntity, OrderDetails.class);
            Logging.responseFromBackend(gson.toJson(orderDetails), Constants.ORDER_DETAILS_URI);

            Map<String, Integer> itemMapping = new HashMap<String, Integer>();
            for (Item item : orderDetails.getNotShippedItems()) {
                if (itemMapping.containsKey(item.getState())) {
                    Integer itemCount = itemMapping.get(item.getState()) + 1;
                    itemMapping.put(item.getState(), itemCount);
                } else {
                    itemMapping.put(item.getState(), 1);
                }
            }
            sendText.append("you ordered ");
            for (Entry<String, Integer> entry : itemMapping.entrySet()) {
                sendText.append(entry.getValue() + " item(s) " + "that is/are " + entry.getKey() + ".");
            }
            sendText.append(" Please check the app for more details.");
        } else {
            sendText.append("you have not placed any order yet.");
        }

        ArrayList<String> arrayText = new ArrayList<String>();
        ArrayList<FulfillmentMessages> arrayFulfillmentMessages = new ArrayList<FulfillmentMessages>();
        arrayText.add(sendText.toString());
        text.setText(arrayText);
        fulfillmentMessages.setText(text);
        arrayFulfillmentMessages.add(fulfillmentMessages);
        response.setFulfillmentText(sendText.toString());
        response.setFulfillmentMessages(arrayFulfillmentMessages);
        return response;
    }

    public DialogFlowResponse noAction() {
        Logging.noAction();
        ArrayList<String> arrayText = new ArrayList<String>();
        ArrayList<FulfillmentMessages> arrayFulfillmentMessages = new ArrayList<FulfillmentMessages>();
        arrayText.add(Constants.NO_ACTION);
        text.setText(arrayText);
        fulfillmentMessages.setText(text);
        arrayFulfillmentMessages.add(fulfillmentMessages);
        response.setFulfillmentText(Constants.NO_ACTION);
        response.setFulfillmentMessages(arrayFulfillmentMessages);
        return response;
    }

    public DialogFlowResponse reliable(DialogFlowRequest request) {
        Logging.circuitBreaker();
        ArrayList<String> arrayText = new ArrayList<String>();
        ArrayList<FulfillmentMessages> arrayFulfillmentMessages = new ArrayList<FulfillmentMessages>();
        arrayText.add(Constants.ERROR_500);
        text.setText(arrayText);
        fulfillmentMessages.setText(text);
        arrayFulfillmentMessages.add(fulfillmentMessages);
        response.setFulfillmentText(Constants.ERROR_500);
        response.setFulfillmentMessages(arrayFulfillmentMessages);
        return response;
    }

}
