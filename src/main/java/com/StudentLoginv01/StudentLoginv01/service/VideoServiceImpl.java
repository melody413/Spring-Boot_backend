package com.StudentLoginv01.StudentLoginv01.service;


import com.opentok.OpenTok;
import com.opentok.exception.OpenTokException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl implements VideoService {
    @Value("${vonage.api.key}")
    private int apiKey;

    @Value("${vonage.api.secret}")
    private String apiSecret;

    @Override
    public String createSession() throws OpenTokException {
        OpenTok opentok = new OpenTok(apiKey, apiSecret);
        com.opentok.Session session = opentok.createSession();
        System.out.println("===> " +session.getSessionId());
        return session.getSessionId();
    }
    @Override
    public String generateToken(String sessionId) throws OpenTokException {
        OpenTok opentok = new OpenTok(apiKey, apiSecret);
        String token = opentok.generateToken(sessionId).toString();
        return token;
    }
}
