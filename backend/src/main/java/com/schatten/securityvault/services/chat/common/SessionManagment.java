package com.schatten.securityvault.services.chat.common;

import com.schatten.securityvault.models.UserSession;
import com.schatten.securityvault.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SessionManagment {
    private final Map<String, UserSession> activateSessions = new ConcurrentHashMap<>();
    private final Map<String, Set<String>> peerConnections = new ConcurrentHashMap<>();

    @Autowired
    private UserRepository userRepository;

    public void registerUser(String userId,String sessionId){
        userRepository.findbyEmail();
    }
    public boolean connectedUsers(String user1, String user2){

    }
    public boolean connectedUser(String user){

    }
    public String getSessionId(String userId) {
        UserSession session = activeSessions.get(userId);
        return (session != null) ? session.getSessionId() : null;
    }
}
