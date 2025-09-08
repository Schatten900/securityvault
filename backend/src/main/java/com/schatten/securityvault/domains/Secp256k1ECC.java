package com.schatten.securityvault.domains;
import lombok.Getter;

import java.math.BigInteger;

@Getter
public class Secp256k1ECC {
    @Getter
    protected static final ECPoint G;
    @Getter
    protected static final BigInteger prime = new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFC2F", 16);
    @Getter
    protected static final BigInteger a = BigInteger.ZERO;;
    @Getter
    protected static final BigInteger b = BigInteger.valueOf(7);;
    @Getter
    protected static final BigInteger h = BigInteger.ONE;
    @Getter
    protected static final BigInteger n = new BigInteger(
            "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEBAAEDCE6AF48A03BBFD25E8CD0364141", 16);
    static {
        BigInteger gX = new BigInteger(
                "79BE667EF9DCBBAC55A06295CE870B07029BFCDB2DCE28D959F2815B16F81798", 16);
        BigInteger gY = new BigInteger(
                "483ADA7726A3C4655DA4FBFC0E1108A8FD17B448A68554199C47D08FFB10D4B8", 16);
        G = new ECPoint(gX, gY);
    }

}
