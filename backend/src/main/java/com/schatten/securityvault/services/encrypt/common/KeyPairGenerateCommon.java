package com.schatten.securityvault.services.encrypt.common;

import com.schatten.securityvault.domains.ECPoint;
import com.schatten.securityvault.domains.Secp256k1ECC;
import com.schatten.securityvault.models.KeyPairECC;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;

@Service
@Getter
@Setter
public class KeyPairGenerateCommon {
    private final KeyPairECC keyPairECC;
    private final ECPoint publicKey;

    public KeyPairGenerateCommon() {
        KeyPairECC keyPair = generateKeyPair();
        this.keyPairECC = keyPair;
        this.publicKey = keyPair.getPublic_key();
    }

    private KeyPairECC generateKeyPair() {
        SecureRandom random = new SecureRandom();
        BigInteger len_group = Secp256k1ECC.getN();
        BigInteger privateKey;
        do {
            privateKey = new BigInteger(256, random);
        } while (privateKey.compareTo(len_group) >= 0 || privateKey.equals(BigInteger.ZERO));

        // Calcula a chave pública
        ECPoint base_point = Secp256k1ECC.getG();
        ECPoint publicKey = base_point.multiply(privateKey);

        return new KeyPairECC(privateKey, publicKey);
    }
}
