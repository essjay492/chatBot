package bot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import com.google.gson.Gson;

import bot.beans.DialogFlowRequest;
import bot.beans.DialogFlowResponse;
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
            default:
                response = impl.defaultAction();
                break; 
            }
        }
        Logging.responseSentToDialogFlow(gson.toJson(response));
        return response;
    }
}
