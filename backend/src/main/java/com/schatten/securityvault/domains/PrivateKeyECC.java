package com.schatten.securityvault.domains;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Base64;

@Embeddable
@Getter
public class PrivateKeyECC {
    private byte[] private_Key;
    private int key_size;
    public PrivateKeyECC(){
        this.key_size = 0;
        this.private_Key  = null;
    }
    public PrivateKeyECC(byte[] privateKey) {
        setPrivateKeyECC(privateKey);
    }

    private void setPrivateKeyECC(byte[] private_Key) {
        if (private_Key == null) {
            this.private_Key = null;
            this.key_size = 0; //256
        } else {
            this.private_Key = Arrays.copyOf(private_Key, private_Key.length);
            this.key_size = private_Key.length * 8;
        }
    }

    public String getBase64(){
        return private_Key != null ? Base64.getEncoder().encodeToString(private_Key) : "";
    }
    public boolean isValid(){
        return this.private_Key != null && private_Key.length > 0;
    }
    public int getKeySize() {
        return this.private_Key != null ?  this.key_size : 0;
    }

}
