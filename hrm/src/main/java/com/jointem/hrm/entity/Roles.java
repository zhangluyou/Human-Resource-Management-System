package com.jointem.hrm.entity;

import java.util.List;

public class Roles {
    private Integer id;

    private String description;
    
    private List<Permissions> permissionsList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

	public List<Permissions> getPermissionsList() {
		return permissionsList;
	}

	public void setPermissionsList(List<Permissions> permissionsList) {
		this.permissionsList = permissionsList;
	}

	@Override
	public String toString() {
		return "Roles [id=" + id + ", description=" + description + ", permissionsList=" + permissionsList + "]";
	}
    
	
}