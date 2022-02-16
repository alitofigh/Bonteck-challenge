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
    INQUIRY_USER_STATUS("inquiry:get-user-status"),
    INQUIRY_ALLOWED_SERVICES("inquiry:get-user-allowed-services"),
    INQUIRY_ACTIVE_SERVICES("inquiry:get-user-active-services"),
    INQUIRY_USED_SERVICES("inquiry:show-used-services"),

    /* User management part - when a new service is added to UserManagementController
       this part must be update. */
    MANAGEMENT_ADD_USER("management:register"),
    MANAGEMENT_CHANGE_ROLE("management:change-role"),
    MANAGEMENT_CHANGE_Service_Status("management:change-service-status"),
    MANAGEMENT_CHANGE_Service_ACCESS("management:service-access-to-user"),
    MANAGEMENT_CHANGE_USER_Service_STATUS("management:change-user-service-status"),
    MANAGEMENT_LIST_SERVICES("management:list-services"),
    MANAGEMENT_LIST_USERS("management:list-users");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
