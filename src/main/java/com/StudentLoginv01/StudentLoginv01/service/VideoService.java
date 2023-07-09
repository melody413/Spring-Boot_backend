package com.StudentLoginv01.StudentLoginv01.service;

import com.opentok.exception.OpenTokException;

public interface VideoService {
    String createSession() throws OpenTokException;

    String generateToken(String sessionId) throws OpenTokException;
}
