package com.piramida.security.impl;

import org.mindrot.jbcrypt.BCrypt;

import com.piramida.service.security.HashGeneratorService;

public class PasswordGeneratorService implements HashGeneratorService {

    public String generateValue(final String baseKey) {
	return BCrypt.hashpw(baseKey, BCrypt.gensalt());
    }

    public boolean compareValues(final String hashed, final String candidate) {
	return BCrypt.checkpw(candidate, hashed);
    }

}
