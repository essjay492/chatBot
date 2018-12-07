package bot.constants;

public interface Constants {
    
    
//    ======ACTIONS=======
    final String GET_CLUBS_ACTION = "getClubs";
    final String DEFAULT_ACTION = "Work in progress";
    
//    ======MOBILE_PROXY=======
    final String URL = "https://www.walmartmobile.com.mx";
    
    final String GET_CLUBS_URI = URL + "/sams/account/getClubs";
    
//    ======200_OK=======
    
    final String GET_CLUBS_200 = "Stores found";
    
//    ======500_OK=======
    
    final String ERROR_500 = "Server seems to be down, please try again later!";
    
    
//    ======HTTP_METHODS=======
    
    final String GET = "GET";
    final String POST = "POST";
    
//    ======HYSTRIX=======
    
    final String TIMEOUT = "10000";
    

}
