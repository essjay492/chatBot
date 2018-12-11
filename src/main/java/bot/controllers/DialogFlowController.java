package bot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import com.google.gson.Gson;

import bot.beans.dialogFlow.DialogFlowRequest;
import bot.beans.dialogFlow.DialogFlowResponse;
import bot.constants.Constants;
import log.Logging;

@RestController
public class DialogFlowController {
    
    @Autowired
    private DialogFlowResponse response;
    @Autowired
    private ControllerImpl impl;

    private Gson gson = new Gson();

    @ResponseBody
    @PostMapping("/sams/chatBot")
    public DialogFlowResponse rendering(@RequestBody DialogFlowRequest request) {
        Logging.requestFromDialogFlow(gson.toJson(request));
        String action = request.getQueryResult().getAction();
        if (action != null) {
            switch (action) {
            case Constants.GET_CLUBS_ACTION:
                response = impl.getClubs(request);
                break;
            case Constants.ORDER_DETAILS_ACTION:
                response = impl.orderDetails(request);
                break;
            case Constants.SEARCH_ACTION:
                response = impl.searchItem(request);
                break;
            default:
                response = impl.defaultAction();
                break; 
            } 
        } else {
            response = impl.noAction(); 
        }
        Logging.responseSentToDialogFlow(gson.toJson(response));
        return response;
    }
}
