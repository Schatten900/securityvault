package com.schatten.securityvault.models;
import com.schatten.securityvault.domains.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.SecureRandom;

@Getter
@Setter
public class KeyPairECC {
    @Getter
    @Setter
    BigInteger private_key;
    @Getter
    @Setter
    ECPoint public_key;

    public KeyPairECC() {
        this.private_key = null;
        this.public_key = null;
    }
    public KeyPairECC(BigInteger private_key, ECPoint public_key) {
        this.private_key = private_key;
        this.public_key = public_key;
    }
}
