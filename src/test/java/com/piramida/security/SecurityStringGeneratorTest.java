package com.piramida.security;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.piramida.security.impl.SecurityStringGeneratorService;

public class SecurityStringGeneratorTest {
    private static final String CORECT_BASE_KEY = "corectBaseKey";
    private SecurityStringGeneratorService testInstance;

    @Before
    public void setUp() {
	testInstance = new SecurityStringGeneratorService();
    }

    @Test
    public void shouldHashPassword() {
	final String hashed = testInstance.generateValue(CORECT_BASE_KEY);
	Assert.assertNotNull("No value was generated", hashed);

    }
}