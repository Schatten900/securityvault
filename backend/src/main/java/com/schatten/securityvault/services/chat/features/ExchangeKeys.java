package com.schatten.securityvault.services.chat.features;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.schatten.securityvault.services.encrypt.features.GenerateSharedSecret;

@Service
public class ExchangeKeys {
    @Autowired
    GenerateSharedSecret generateSharedSecret;

    public void exchange(){
        // Using webSocket to exchange keys
    }
}
