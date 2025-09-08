package com.schatten.securityvault.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.schatten.securityvault.domains.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Base64;

@Entity
@Getter
@Setter
@Table(name = "Perfil")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "Email", nullable = false, unique = true, length = 255))
    })
    private Email email;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "EncryptedPassword", nullable = false, length = 500, unique = true))
    })
    private EncryptedPassword encryptedPassword;

    @Transient
    @JsonIgnore
    private transient Password password;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "username", nullable = false, length = 100))
    })
    private Name name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "PublicKey", nullable = false, columnDefinition = "TEXT"))
    })
    private PublicKeyECC publicKey;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "EncryptedPrivateKey", nullable = false, columnDefinition = "TEXT"))
    })
    private PrivateKeyECC privateKey;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "Salt", nullable = false, length = 44))
    })
    private String salt;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "Iv", nullable = false, length = 24))
    })
    private String iv;

    @Lob
    private byte[] image;

    public User() {
        this.image = null;
    }

    public User(Email email, Name name, EncryptedPassword encryptedPassword,
                PublicKeyECC publicKey, PrivateKeyECC encryptedPrivateKey,
                String salt, String iv) {
        this.email = email;
        this.name = name;
        this.encryptedPassword = encryptedPassword;
        this.publicKey = publicKey;
        this.privateKey = encryptedPrivateKey;
        this.salt = salt;
        this.iv = iv;
        this.image = null;
    }

    public String getImageBase64() {
        if (this.image == null) {
            return null;
        }
        return Base64.getEncoder().encodeToString(this.image);
    }
}