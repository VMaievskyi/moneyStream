package com.piramida.service.security;

public interface HashGeneratorService {

    String generateValue(String baseKey);

    boolean compareValues(String hashed, String candidate);
}
