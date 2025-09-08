package com.schatten.securityvault.services.encrypt.features;

import com.schatten.securityvault.domains.ECPoint;
import com.schatten.securityvault.domains.Pair;
import com.schatten.securityvault.domains.Secp256k1ECC;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;

@Service
public class GenerateKeyPairECC {
    public Pair<BigInteger, ECPoint> generateKey(){
        BigInteger n = Secp256k1ECC.getN();
        SecureRandom secureRandom = new SecureRandom();

        BigInteger privateKey;
        do {
            privateKey = new BigInteger(256, secureRandom);
        } while (privateKey.compareTo(BigInteger.ONE) < 0 || privateKey.compareTo(n) >= 0);

        ECPoint point_G = Secp256k1ECC.getG();
        ECPoint publicKey = point_G.multiply(privateKey);   // G * d

        return new Pair<>(privateKey, publicKey);
    }
}
