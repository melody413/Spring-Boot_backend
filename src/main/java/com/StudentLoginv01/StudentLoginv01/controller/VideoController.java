package com.StudentLoginv01.StudentLoginv01.controller;

import com.StudentLoginv01.StudentLoginv01.service.VideoService;
import com.opentok.exception.OpenTokException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/video")
public class VideoController {
    @Autowired
    private VideoService videoService;


    @PostMapping("/session")
    public ResponseEntity<String> createSession() throws OpenTokException {
        String sessionId = videoService.createSession();
        return ResponseEntity.ok(sessionId);
    }

    @PostMapping("/token")
    public ResponseEntity<String> generateToken(@PathVariable String session) throws OpenTokException {
        System.out.println("=======>  "+session);
        String token = videoService.generateToken(session);
        return ResponseEntity.ok(token);
    }
}
