package com.desafio.backendsbr.services.impl;

import com.desafio.backendsbr.services.EncryptService;
import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.stereotype.Service;

@Service
public class EncryptServiceImpl implements EncryptService {
    private final AES256TextEncryptor textEncryptor;

    public EncryptServiceImpl(AES256TextEncryptor textEncryptor) {
        this.textEncryptor = textEncryptor;
    }

    @Override
    public String encrypt(String data) throws NullPointerException {
        if (data == null || data.isEmpty()) {
            throw new NullPointerException("The data to be encrypted cannot be empty.");
        }
        return textEncryptor.encrypt(data);
    }

    @Override
    public String decrypt(String data) throws NullPointerException {
        if (data == null || data.isEmpty()) {
            throw new NullPointerException("The data to be decrypted cannot be empty.");
        }
        return textEncryptor.decrypt(data);
    }
}
