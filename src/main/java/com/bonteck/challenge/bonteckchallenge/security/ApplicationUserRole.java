package com.bonteck.challenge.bonteckchallenge.security;

import com.bonteck.challenge.bonteckchallenge.exception.UnknownRoleException;
import com.google.common.collect.Sets;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.bonteck.challenge.bonteckchallenge.security.ApplicationUserPermission.*;

/**
 * @author Ali Tofigh 2/10/2022 12:03 PM
 */

@Getter
public enum ApplicationUserRole {
    // Inquiry controller access part
    LEVEL_1(1, Sets.newHashSet(INQUIRY_USER_STATUS, INQUIRY_ACTIVE_SERVICES)),
    LEVEL_2(2, Sets.newHashSet(INQUIRY_ALLOWED_SERVICES)),

    // communication controller access part
    LEVEL_3(3, Sets.newHashSet(COMMUNICATION_SMS)),
    LEVEL_4(4, Sets.newHashSet(COMMUNICATION_EMAIL)),
    LEVEL_5(5, Sets.newHashSet(COMMUNICATION_NEWS)),

    // management controller access part
    LEVEL_6(6, Sets.newHashSet(USER_MANAGEMENT_LIST_USERS)),
    LEVEL_7(7, Sets.newHashSet(USER_MANAGEMENT_ADD_USER)),
    LEVEL_8(8, Sets.newHashSet(USER_MANAGEMENT_CHANGE_ROLE)),
    ADMIN(9,
            Sets.newHashSet(INQUIRY_USER_STATUS, INQUIRY_ACTIVE_SERVICES, INQUIRY_ALLOWED_SERVICES,
                    COMMUNICATION_SMS, COMMUNICATION_EMAIL, COMMUNICATION_NEWS, USER_MANAGEMENT_ADD_USER,
                    USER_MANAGEMENT_LIST_USERS, USER_MANAGEMENT_CHANGE_ROLE));


    private final int roleId;
    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(int rolId, Set<ApplicationUserPermission> permissions) {
        this.roleId = rolId;
        this.permissions = permissions;
    }

    public static ApplicationUserRole getRole(int roleId) throws UnknownRoleException {
        for (ApplicationUserRole role : values()) {
            if (role.getRoleId() == roleId)
                return role;
        }
        throw new UnknownRoleException();
    }

    public Set<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
