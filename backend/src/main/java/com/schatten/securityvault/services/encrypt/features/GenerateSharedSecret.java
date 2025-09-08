package com.schatten.securityvault.services.encrypt.features;
import com.schatten.securityvault.domains.ECPoint;
import com.schatten.securityvault.models.KeyPairECC;
import com.schatten.securityvault.services.encrypt.common.DeriveKeyCommon;
import com.schatten.securityvault.services.encrypt.common.KeyPairGenerateCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;


@Service
public class GenerateSharedSecret {
    @Autowired
    private KeyPairGenerateCommon keypaircommon;
    @Autowired
    private DeriveKeyCommon derivekey;

    public byte[] generateSecret(ECPoint OtherPublicKeyECC) {
        KeyPairECC keypairEcc = this.keypaircommon.getKeyPairECC();
        BigInteger userPrivateKeyECC = keypairEcc.getPrivate_key();
        ECPoint shared_point = OtherPublicKeyECC.multiply(userPrivateKeyECC);

        byte[] infoEncrypt = "AES-256-GCM:EncryptionKeyV1"
                .getBytes(StandardCharsets.UTF_8);

        // salt calculation
        byte[] salt = new byte[32];
        new SecureRandom().nextBytes(salt);

        // key aes derivation
        BigInteger sharedSecretX = shared_point.getX();
        byte[] sharedSecretBytes = sharedSecretX.toByteArray();
        return this.derivekey.deriveKey(sharedSecretBytes,salt,infoEncrypt,32);
    }
}
