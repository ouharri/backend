package com.ouharri.aftas.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enum representing various permissions that can be granted to roles in the system.
 * Each permission corresponds to a specific action or set of actions that a user is allowed to perform.
 *
 * @author <a href="mailto:ouharrioutman@gmail.com">Ouharri Outman</a>
 */
@Getter
@RequiredArgsConstructor
public enum Permission {

    /**
     * Permission to read admin-related data.
     */
    ADMIN_READ("admin:read"),

    /**
     * Permission to update admin-related data.
     */
    ADMIN_UPDATE("admin:update"),

    /**
     * Permission to create admin-related data.
     */
    ADMIN_CREATE("admin:create"),

    /**
     * Permission to delete admin-related data.
     */
    ADMIN_DELETE("admin:delete"),

    /**
     * Permission to read management-related data.
     */
    MANAGER_READ("management:read"),

    /**
     * Permission to update management-related data.
     */
    MANAGER_UPDATE("management:update"),

    /**
     * Permission to create management-related data.
     */
    MANAGER_CREATE("management:create"),

    /**
     * Permission to delete management-related data.
     */
    MANAGER_DELETE("management:delete"),

    /**
     * Permission to read member-related data.
     */
    MEMBER_READ("member:read"),

    /**
     * Permission to update member-related data.
     */
    MEMBER_UPDATE("member:update"),

    /**
     * Permission to create member-related data.
     */
    MEMBER_CREATE("member:create"),

    /**
     * Permission to delete member-related data.
     */
    MEMBER_DELETE("member:delete"),

    /**
     * Permission to read jury-related data.
     */
    JURY_READ("jury:read"),

    /**
     * Permission to update jury-related data.
     */
    JURY_UPDATE("jury:update"),

    /**
     * Permission to create jury-related data.
     */
    JURY_CREATE("jury:create"),

    /**
     * Permission to delete jury-related data.
     */
    JURY_DELETE("jury:delete"),

    /**
     * Permission for super administrator to read data. Generally grants access to more sensitive or comprehensive data.
     */
    SUPER_ADMINISTRATOR_READ("super-admin:read"),

    /**
     * Permission for super administrator to update data.
     */
    SUPER_ADMINISTRATOR_UPDATE("super-admin:update"),

    /**
     * Permission for super-administrator to create data.
     */
    SUPER_ADMINISTRATOR_CREATE("super-admin:create"),

    /**
     * Permission for super administrator to delete data.
     */
    SUPER_ADMINISTRATOR_DELETE("super-admin:delete");

    private final String permission;
}