package com.StudentLoginv01.StudentLoginv01.controller;


import com.StudentLoginv01.StudentLoginv01.configuration.ChatMessage;
import com.StudentLoginv01.StudentLoginv01.configuration.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;


@RestController
public class MessageController {

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public ResponseMessage greeting(@RequestBody ChatMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay

        System.out.println(message.getMessageContent()+ "===========");
        message.setMessageContent(message.getMessageContent().concat(" !!!!)"));
      //  messagingTemplate.convertAndSend("/topic/messages", message);
        return new ResponseMessage(HtmlUtils.htmlEscape(message.getMessageContent()));

    }
}
