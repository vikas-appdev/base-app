package com.gradlic.login.constants;

public class Constants {
    public static final String USER_AUTHORITIES = "document:create,document:update,document:delete,document:read";
    public static final String ADMIN_AUTHORITIES = "user:create,user:update,user:read,document:create,document:update,document:delete,document:read";
    public static final String SUPER_ADMIN_AUTHORITIES = "user:create,user:update,user:read,user:delete,document:create,document:update,document:delete,document:read";
    public static final String MANAGER_AUTHORITIES = "document:create,document:update,document:delete,document:read";
}
