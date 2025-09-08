package com.schatten.securityvault.services.encrypt.common;

import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.generators.HKDFBytesGenerator;
import org.bouncycastle.crypto.params.HKDFParameters;
import org.springframework.stereotype.Service;

@Service
public class DeriveKeyCommon {

    public byte[] deriveKey(byte[] secret, byte[] salt, byte[] info, int length) {
        HKDFBytesGenerator hkdf = new HKDFBytesGenerator(new SHA256Digest());
        HKDFParameters params = new HKDFParameters(secret,salt,info);
        hkdf.init(params);
        byte[] derivedKey = new byte[length];
        hkdf.generateBytes(derivedKey,0,length);
        return derivedKey;
    }
}
