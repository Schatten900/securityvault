package com.schatten.securityvault.domains;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Base64;

@Embeddable
@Getter
@Setter
public class PublicKeyECC {
    private byte[] public_Key;
    private int key_size;

    public PublicKeyECC() {
        this.key_size = 0; //520
        this.public_Key  = null;
    }
    public PublicKeyECC(byte[] publicKey) {
        setPublicKeyECC(publicKey);
    }

    private void setPublicKeyECC(byte[] publicKey) {
        if (publicKey == null) {
            this.public_Key = null;
            this.key_size = 0;
        } else {
            this.public_Key = Arrays.copyOf(publicKey, publicKey.length);
            this.key_size = publicKey.length * 8;
        }
    }

    public String getBase64(){
        return public_Key != null ? Base64.getEncoder().encodeToString(public_Key) : "";
    }
    public boolean isValid(){
        return this.public_Key != null && public_Key.length > 0;
    }
    public int getKeySize() {
        return this.public_Key != null ?  this.key_size : 0;
    }
}
