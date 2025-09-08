package com.schatten.securityvault.domains;
import lombok.Getter;
import java.math.BigInteger;
import java.util.Objects;

@Getter
public class ECPoint {
    private static final ECPoint POINT_AT_INFINITY = new ECPoint(null, null);
    private final BigInteger x;
    private final BigInteger y;
    public ECPoint(BigInteger x, BigInteger y) {
        this.x = x;
        this.y = y;
    }
    public boolean isPointAtInfinity() {
        return x == null && y == null;
    }
    public boolean isOnCurve() {
        if (isPointAtInfinity())
            return true;

        BigInteger lhs = this.y.modPow(BigInteger.TWO, Secp256k1ECC.prime);

        // x³ + a*x + b mod p
        BigInteger rhs = this.x.modPow(BigInteger.valueOf(3), Secp256k1ECC.prime)
                .add(Secp256k1ECC.a.multiply(this.x)) // + a*x
                .add(Secp256k1ECC.b)                  // + b
                .mod(Secp256k1ECC.prime);             // mod p

        return lhs.equals(rhs);
    }

    public ECPoint add(ECPoint point_Q) {
        if (!this.isOnCurve() || !point_Q.isOnCurve()) {
            throw new IllegalArgumentException("Um ou ambos os pontos não estão na curva!");
        }
        // P + 0 = P
        if (this.isPointAtInfinity()) return point_Q;
        if (point_Q.isPointAtInfinity()) return this;
        // P + (-P) = 0
        if (this.x.equals(point_Q.x) && this.y.add(point_Q.y).mod(Secp256k1ECC.prime).equals(BigInteger.ZERO))
            return POINT_AT_INFINITY;

        BigInteger angular_coefficient;
        if (!this.equals(point_Q)) {
            // P != Q
            angular_coefficient = point_Q.y.subtract(this.y)
                    .multiply(point_Q.x.subtract(this.x)
                    .modInverse(Secp256k1ECC.prime))
                    .mod(Secp256k1ECC.prime);
        }
        else{
            // 2P
            BigInteger numerator = point_Q.x.pow(2)
                    .multiply(BigInteger.valueOf(3))
                    .add(Secp256k1ECC.a);


            BigInteger denominator = this.y.multiply(BigInteger.TWO);
            BigInteger inverse_denominator = denominator.modInverse(Secp256k1ECC.prime);
            angular_coefficient = numerator.multiply(inverse_denominator)
                    .mod(Secp256k1ECC.prime);
        }
        // y = mx + n onde m eh o coeficiente angular
        // usando do algoritmo de viete eh possivel deduzir que x3 = m² - x1 - x2
        BigInteger xR = angular_coefficient.pow(2).subtract(this.x).subtract(point_Q.x).mod(Secp256k1ECC.prime);
        // m(xP - xR) - yP
        BigInteger yR = angular_coefficient.multiply(this.x.subtract(xR))
                .subtract(this.y)
                .mod(Secp256k1ECC.prime);

        return new ECPoint(xR,yR);
    }

    public ECPoint double_point(){
        return this.add(this);
    }

    public ECPoint multiply(BigInteger k){
        // calculates k * P with algorithm add-and-double
        k = k.mod(Secp256k1ECC.n);
        if (k.equals(BigInteger.ZERO))
            return POINT_AT_INFINITY;

        ECPoint current = this;
        ECPoint result = POINT_AT_INFINITY;

        for (int i=0; i < k.bitLength(); i++){
            if (k.testBit(i)){
                result = result.add(current);
            }
            current = current.double_point();
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ECPoint otherPoint = (ECPoint) obj;

        if (this.isPointAtInfinity() && otherPoint.isPointAtInfinity()) {
            return true;
        }
        if (this.isPointAtInfinity() || otherPoint.isPointAtInfinity()) {
            return false;
        }
        return Objects.equals(this.x, otherPoint.x) && Objects.equals(this.y, otherPoint.y);
    }
    @Override
    public int hashCode() {
        if (isPointAtInfinity()) {
            return 0;
        }
        return Objects.hash(x, y);
    }
}
