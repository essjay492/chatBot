package bot.constants;

public interface Constants {
    
    
//    ======ACTIONS=======
    final String GET_CLUBS_ACTION = "getClubs";
    final String ORDER_DETAILS_ACTION = "orderDetails";
    final String DEFAULT_ACTION = "Please contact customer service at 01-800-999-72-67 for more details.";
    final String SEARCH_ACTION = "search";
    
//    ======MOBILE_PROXY=======
    final String DEV_URL = "https://www.walmartmobile.com.mx";
    final String QA_URL = "http://mex-sams-mobileproxy-qa.walmart.com";
    
    final String GET_CLUBS_URI = DEV_URL + "/sams/account/getClubs";
    
    final String ORDER_DETAILS_URI = DEV_URL + "/sams/auth/checkout/getOrderDetails";
    final String ORDER_LIST_URI = DEV_URL + "/sams/auth/account/customOrderLookUp";
    
    final String LOGIN_URI = DEV_URL + "/sams/account/login";
    
    final String SEARCH_URL = DEV_URL + "/sams/PLP/getProductsPLPBySearch";
    
//    ======200_OK=======
    
    final String GET_CLUBS_200 = "Stores found";
    
//    ======500_ERROR=======
    
    final String ERROR_500 = "Server seems to be down, please try again later!";
    
    
//    ======HTTP_METHODS=======
    
    final String GET = "GET";
    final String POST = "POST";
    
//    ======HYSTRIX=======
    
    final String TIMEOUT = "10000";
    
    final String DEFAULT_STORE_ID = "0000009999";
    
    
    

}
