package com.paazl.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
public class MessageController {
    private final Provider provider;
    private AtomicInteger counter = new AtomicInteger();


    @GetMapping("/send/{message}")
    public String send(@PathVariable String message){
        provider.sendMesasge(message);
        return "Message <" + message + "> sent!";
    }


    @GetMapping("/test/{message}/{times}")
    public String sendNTimes(@PathVariable String message, @PathVariable Integer times){

        Stream.generate(TestMessage::builder)
                .limit(times)
                .map(builder -> builder.id(counter.getAndIncrement()).message(message).build())
                .forEach(provider::sendObject);

        return "Messages sent!";
    }

    @GetMapping("/topic/{message}")
    public String sendToTopic(@PathVariable String message){

        provider.sendToTopic(message);
        return "Message sent to topic!";
    }
}
