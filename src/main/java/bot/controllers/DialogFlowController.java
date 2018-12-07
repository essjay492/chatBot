package bot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    private RestTemplate restTemplate = new RestTemplate();

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
            }
        }
        Logging.responseSentToDialogFlow(gson.toJson(response));
        return response;
    }

    @ResponseBody
    @GetMapping("/sams")
    public String greeting() {
        String result = restTemplate.getForObject(Constants.GET_CLUBS_URI, String.class);
        return result;
    }
}
