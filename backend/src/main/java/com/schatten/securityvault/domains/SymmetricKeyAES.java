package com.schatten.securityvault.domains;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Base64;

@Embeddable
@Getter
public class SymmetricKeyAES {
    private byte[] symmetric_key;
    private int key_size;
    public SymmetricKeyAES() {
        this.symmetric_key = null;
        this.key_size = 0; // 128
    }
    public SymmetricKeyAES(byte[] symmetric_key) {
        if (symmetric_key == null){
            this.symmetric_key = null;
            this.key_size = 0;
        }
        else{
            this.symmetric_key = Arrays.copyOf(symmetric_key,symmetric_key.length);
            this.key_size = symmetric_key.length * 8;
        }
    }
    public String getBase64(){
        return symmetric_key != null ? Base64.getEncoder().encodeToString(symmetric_key) : "";
    }
    public boolean isValid(){
        return this.symmetric_key != null && symmetric_key.length > 0;
    }
    public int getKeySize() {
        return this.symmetric_key != null ? this.key_size : 0;
    }
}
