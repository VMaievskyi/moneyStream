package com.piramida.entity;

public enum EmailType {
    REGISTRATION("registration");

    private String subject;

    private EmailType(final String subject) {
	this.subject = subject;
    }

    public String getSubject() {
	return subject;
    }
}
