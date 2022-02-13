package com.bonteck.challenge.bonteckchallenge.security;

/**
 * @author Ali Tofigh 2/10/2022 12:04 PM
 */

public enum ApplicationUserPermission {
    /* communication part - when a new service is added to communicationController
       this part must be update. */
    COMMUNICATION_SMS("communication:sms"),
    COMMUNICATION_EMAIL("communication:email"),
    COMMUNICATION_NEWS("communication:news"),

    /* Inquiry part - when a new service is added to InquiryController
       this part must be update. */
    INQUIRY_USER_STATUS("inquiry:user status"),
    INQUIRY_ALLOWED_SERVICES("inquiry:allowed services"),
    INQUIRY_ACTIVE_SERVICES("inquiry:active services"),

    /* User management part - when a new service is added to UserManagementController
       this part must be update. */
    USER_MANAGEMENT_ADD_USER("user-management:add user"),
    USER_MANAGEMENT_CHANGE_ROLE("user-management:change role"),
    USER_MANAGEMENT_LIST_USERS("user-management:all users");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
