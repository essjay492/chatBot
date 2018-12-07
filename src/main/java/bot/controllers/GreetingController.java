package bot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import bot.beans.Greeting;

@RestController
public class GreetingController {

    @ResponseBody
    @GetMapping("/greeting")
    public Greeting greeting() {
        return new Greeting("Hi, I am Sams Chat Bot. Nice to meet you!");
    }
}
