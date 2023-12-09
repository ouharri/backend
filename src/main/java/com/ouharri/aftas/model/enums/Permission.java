package com.ouharri.aftas.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),

    MANAGER_READ("management:read"),
    MANAGER_UPDATE("management:update"),
    MANAGER_CREATE("management:create"),
    MANAGER_DELETE("management:delete"),

    SUPER_ADMINISTRATOR_READ("admin:read"),
    SUPER_ADMINISTRATOR_UPDATE("admin:update"),
    SUPER_ADMINISTRATOR_CREATE("admin:create"),
    SUPER_ADMINISTRATOR_DELETE("admin:delete");

    private final String permission;
}
