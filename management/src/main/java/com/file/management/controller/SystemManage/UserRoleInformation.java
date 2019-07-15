package com.file.management.controller.SystemManage;

public class UserRoleInformation {
    private String name;
    private String username;
    private String department;
    private String role;
    private String authorizer;
    private String authorize_time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAuthorizer() {
        return authorizer;
    }

    public void setAuthorizer(String authorizer) {
        this.authorizer = authorizer;
    }

    public String getAuthorize_time() {
        return authorize_time;
    }

    public void setAuthorize_time(String authorize_time) {
        this.authorize_time = authorize_time;
    }
}
