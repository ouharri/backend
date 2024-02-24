package com.ouharri.aftas.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Enum representing different roles in the system, each with a specific set of permissions.
 * Roles are used to grant authority and access control within the application.
 *
 * @author <a href="mailto:ouharrioutman@gmail.com">Ouharri Outman</a>
 */
@Getter
@RequiredArgsConstructor
public enum Role {

    /**
     * Represents a basic user with no special permissions.
     */
    USER(Collections.emptySet()),

    /**
     * Represents an administrator with a broad range of permissions, including management and jury-related operations.
     */
    ADMINISTRATOR(
            Set.of(
                    Permission.ADMIN_READ,
                    Permission.ADMIN_UPDATE,
                    Permission.ADMIN_DELETE,
                    Permission.ADMIN_CREATE,
                    Permission.MANAGER_READ,
                    Permission.MANAGER_UPDATE,
                    Permission.MANAGER_DELETE,
                    Permission.MANAGER_CREATE,
                    Permission.JURY_READ,
                    Permission.JURY_UPDATE,
                    Permission.JURY_DELETE,
                    Permission.JURY_CREATE
            )
    ),
    /**
     * Represents a super administrator with the highest level of permissions, including all administrator permissions and additional super administrator privileges.
     */
    SUPER_ADMINISTRATOR(
            Set.of(
                    Permission.ADMIN_READ,
                    Permission.ADMIN_UPDATE,
                    Permission.ADMIN_DELETE,
                    Permission.ADMIN_CREATE,
                    Permission.MANAGER_READ,
                    Permission.MANAGER_UPDATE,
                    Permission.MANAGER_DELETE,
                    Permission.MANAGER_CREATE,
                    Permission.JURY_READ,
                    Permission.JURY_UPDATE,
                    Permission.JURY_DELETE,
                    Permission.JURY_CREATE,
                    Permission.SUPER_ADMINISTRATOR_READ,
                    Permission.SUPER_ADMINISTRATOR_UPDATE,
                    Permission.SUPER_ADMINISTRATOR_DELETE,
                    Permission.SUPER_ADMINISTRATOR_CREATE
            )
    ),
    /**
     * Represents a manager role with permissions specific to managerial tasks.
     */
    MANAGER(
            Set.of(
                    Permission.MANAGER_READ,
                    Permission.MANAGER_UPDATE,
                    Permission.MANAGER_DELETE,
                    Permission.MANAGER_CREATE
            )
    ),
    /**
     * Represents a player role with permissions relevant to player activities.
     */
    MEMBER(
            Set.of(
                    Permission.MEMBER_READ,
                    Permission.MEMBER_CREATE,
                    Permission.MEMBER_DELETE,
                    Permission.MEMBER_UPDATE
            )
    ),
    /**
     * Represents a jury member with permissions specific to jury responsibilities.
     */
    JURY(
            Set.of(
                    Permission.JURY_READ,
                    Permission.JURY_UPDATE,
                    Permission.JURY_DELETE,
                    Permission.JURY_CREATE
            )
    );

    private final Set<Permission> permissions;

    /**
     * Converts the role's permissions into a list of SimpleGrantedAuthority objects.
     * This list includes the role itself and all its associated permissions.
     *
     * @return List of SimpleGrantedAuthority objects representing the role and its permissions.
     */
    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        authorities.addAll(getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .toList()
        );
        return authorities;
    }

}