package log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bot.constants.Constants;

public class Logging {
    
    private static Logger logger = LogManager.getLogger(Logging.class);
    
    public static void requestFromDialogFlow(Object request) {
        logger.debug("Request from Dialog Flow");
        logger.debug(request);
    }
    
    public static void requestSentToBackend(Object request, String api, String http_method) {
        logger.debug("A " + http_method + " Request has been sent to backend api :" + api);
        logger.debug(request);
    }
    
    public static void responseFromBackend(Object response, String api) {
        logger.debug("Response from backend api :" + api);
        logger.debug(response);
    }
    
    public static void responseSentToDialogFlow(Object response) {
        logger.debug("Response sent to Dialog Flow");
        logger.debug(response);
    }
    
    public static void circuitBreaker() {
        logger.debug(Constants.ERROR_500);
    }

    public static void defaultAction() {
        logger.debug(Constants.DEFAULT_ACTION);   
    }

}
