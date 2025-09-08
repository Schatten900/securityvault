package com.schatten.securityvault.services.user.features;

import com.schatten.securityvault.domains.Email;
import com.schatten.securityvault.domains.Name;
import com.schatten.securityvault.domains.Password;
import com.schatten.securityvault.repositories.UserRepository;
import com.schatten.securityvault.services.encrypt.common.DeriveKeyCommon;
import com.schatten.securityvault.services.encrypt.common.EncryptAESCommon;
import com.schatten.securityvault.services.encrypt.common.KeyPairGenerateCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterUser {

    @Autowired
    private UserRepository userRepository;   // armazenar no banco de dados
    @Autowired
    private DeriveKeyCommon deriveKeyCommon; // gerar chave para criptografar chave privada usando a senha do usuario
    @Autowired
    private KeyPairGenerateCommon keyPairCommon;    // gerar chaves ECC

    @Autowired
    private EncryptAESCommon encryptAESCommon;

    @Autowired
    private DeriveKeyCommon decryptKeyCommon;

    public boolean register(Name name, Email email, Password password) {
        // generate salt and iv here
        // hash password here to store
    }
}
