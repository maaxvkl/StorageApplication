package storage.constant;

public class Authority {
    public static final String[] USER_AUTHORITIES = { "user:read" };
    public static final String[] HR_AUTHORITIES = { "user:read", "user:update" };
    public static final String[] MANAGER_AUTHORITIES = { "user:read", "user:update" };
    public static final String[] ADMIN_AUTHORITIES = { "store:read", "store:create", "store:update", "store:delete" };
    public static final String[] SUPER_ADMIN_AUTHORITIES = { "store:read", "store:create", "store:update", "store:delete" };
}
