package com.schatten.securityvault.services.encrypt.common;

import org.springframework.stereotype.Service;

@Service
public class DecryptAESCommon {
    // Decrypt message and private key

    private static final String AES_GCM_NO_PADDING = "AES/GCM/NoPadding";
    private static final int GCM_TAG_LENGTH = 128; // bits
    private static final int IV_LENGTH = 12; // bytes (96 bits)

    public byte[] decrypt(String encryptedData, byte[] aesKey, byte[] iv){

    }
}
