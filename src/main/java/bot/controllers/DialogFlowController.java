package bot.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bot.beans.DialogFlowRequest;

@RestController
public class DialogFlowController {

    @RequestMapping("/sams/chatBot")
    public String greeting() {
        return "hello";
    }
}
