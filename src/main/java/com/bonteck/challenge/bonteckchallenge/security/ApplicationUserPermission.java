package com.bonteck.challenge.bonteckchallenge.security;

/**
 * @author Ali Tofigh 2/10/2022 12:04 PM
 */

public enum ApplicationUserPermission {
    COMMUNICATION_READ("communication:read"),
    COMMUNICATION_WRITE("communication:write"),
    INQUIRY_READ("inquiry:read"),
    INQUIRY_WRITE("inquiry:write"),
    USER_MANAGEMENT_READ("user-management:read"),
    USER_MANAGEMENT_WRITE("user-management:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
