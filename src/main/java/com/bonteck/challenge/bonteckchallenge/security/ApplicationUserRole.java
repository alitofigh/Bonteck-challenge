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
    NORMAL_USER_LOW(0, Sets.newHashSet()),
    NORMAL_USER(1, Sets.newHashSet(INQUIRY_READ)),
    HIGH_USER(2, Sets.newHashSet(INQUIRY_READ, INQUIRY_WRITE)),
    SUPER_USER_1(3, Sets.newHashSet(INQUIRY_READ, INQUIRY_WRITE, COMMUNICATION_READ)),
    SUPER_USER_2(4, Sets.newHashSet(INQUIRY_READ, INQUIRY_WRITE, COMMUNICATION_READ, COMMUNICATION_WRITE)),
    ADMIN_1(5, Sets.newHashSet(INQUIRY_READ, INQUIRY_WRITE, COMMUNICATION_READ, COMMUNICATION_WRITE,
            USER_MANAGEMENT_READ)),
    ADMIN_2(6, Sets.newHashSet(INQUIRY_READ, INQUIRY_WRITE, COMMUNICATION_READ, COMMUNICATION_WRITE,
            USER_MANAGEMENT_READ, USER_MANAGEMENT_WRITE));

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
