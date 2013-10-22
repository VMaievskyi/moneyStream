package com.piramida.security;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.piramida.service.security.impl.PasswordGeneratorService;

public class PasswordGeneratorServiceTest {

    private static final String INCORRECT_BASE_KEY = "incorrectBaseKey";
    private static final String CORECT_BASE_KEY = "corectBaseKey";
    private PasswordGeneratorService testInstance;

    @Before
    public void setUp() {
	testInstance = new PasswordGeneratorService();
    }

    @Test
    public void shouldHashPassword() {
	final String hashed = testInstance.generateValue(CORECT_BASE_KEY);
	Assert.assertNotNull("No value was generated", hashed);

    }

    @Test
    public void shouldAcceptPassword() {
	final String hashed = testInstance.generateValue(CORECT_BASE_KEY);
	final boolean result = testInstance.compareValues(hashed,
		CORECT_BASE_KEY);
	Assert.assertTrue("Passwords doesn't match", result);
    }

    @Test
    public void shouldRejectPassword() {
	final String hashed = testInstance.generateValue(INCORRECT_BASE_KEY);
	final boolean result = testInstance.compareValues(hashed,
		CORECT_BASE_KEY);
	Assert.assertFalse("Passwords doesn't match", result);
    }
}
