package bot.constants;

public interface Constants {
    
    
//    ======ACTIONS=======
    final String GET_CLUBS_ACTION = "getClubs";
    final String ORDER_DETAILS_ACTION = "orderDetails";
    final String DEFAULT_ACTION = "Please contact customer service at 01-800-999-72-67 for more details.";
    final String SEARCH_ACTION = "search";
    final String NO_ACTION = "Currently, I don't know how to cater your request. Please contact customer service at 01-800-999-72-67 for more details.";

    
//    ======MOBILE_PROXY_URL=======
    final String PROD_URL = "https://www.walmartmobile.com.mx";
    
    final String GET_CLUBS_URI = PROD_URL + "/sams/account/getClubs";
    final String ORDER_DETAILS_URI = PROD_URL + "/sams/auth/checkout/getOrderDetails";
    final String ORDER_LIST_URI = PROD_URL + "/sams/auth/account/customOrderLookUp";
    final String LOGIN_URI = PROD_URL + "/sams/account/login";
    final String SEARCH_URL = PROD_URL + "/sams/PLP/getProductsPLPBySearch";
    
//    ======200_OK=======
    
    final String GET_CLUBS_200 = "Stores found";
    
//    ======5XX_ERROR=======
    
    final String ERROR_500 = "Server seems to be down, please try again later!";
    
//    ======4XX_ERROR=======
    
    final String GET_CLUBS_404 = "Too many Sams club in Mexico. Please mention the state name where you are looking for the clubs (e.g., get clubs in Mexico)";
    final String GET_STORE_IN_STATE_404 = "Sorry, currently there is no store in your state. We will be opening soon there for you convenience, provided the state you have mentioned is not imaginary.";
    
    
//    ======HTTP_METHODS=======
    
    final String GET = "GET";
    final String POST = "POST";
    
//    ======HYSTRIX=======
    
    final String TIMEOUT = "10000";
    
//    ======RANDOM=======
    
    final String DEFAULT_STORE_ID = "0000009999";
    
    
    

}
