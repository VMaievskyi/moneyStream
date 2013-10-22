package com.piramida.service.security.impl;

import org.apache.commons.lang.NotImplementedException;
import org.mindrot.jbcrypt.BCrypt;

import com.piramida.service.security.HashGeneratorService;

public class SecurityStringGeneratorService implements HashGeneratorService {

    public String generateValue(final String baseKey) {
	return BCrypt.hashpw(baseKey, BCrypt.gensalt());
    }

    public boolean compareValues(final String hashed, final String candidate) {
	throw new NotImplementedException(
		"this feature is not supported in this service");
    }

}
