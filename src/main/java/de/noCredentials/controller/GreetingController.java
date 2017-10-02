package de.noCredentials.controller;

import de.noCredentials.model.EchoModel;
import de.noCredentials.model.Greeting;
import de.noCredentials.model.HelloMessage;
import de.noCredentials.service.SocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    SocketService socketService;

    @MessageMapping("/hello-msg-mapping")
    @SendTo("/topic/greetings")
    public EchoModel greeting(String message) throws Exception {
        System.out.print(message);
        String lala = message;
        String[] lala2 = lala.split("=");
        String lala3 = lala2[1];
        Thread.sleep(1000); // simulated delay
        this.template.convertAndSend("/topic/greetings", lala3);
        return new EchoModel(lala3);
    }

    @RequestMapping(value = "/hello-convert-and-send", method = RequestMethod.POST)
    void echoConvertAndSend(@RequestParam("msg") String message) {
        socketService.echoMessage(message);
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }

}