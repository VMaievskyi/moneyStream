package com.piramida.entity;

public enum UserRoles {
    USER("ROLE_USER"), ADMIN("ROLE_ADMIN");

    private final String role;

    private UserRoles(final String role) {
	this.role = role;
    }

    public String getRole() {
	return role;
    }

}
