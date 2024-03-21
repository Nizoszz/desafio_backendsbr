package com.desafio.backendsbr.services;

public interface EncryptService {
    String encrypt(String data);
    String decrypt(String data);
}