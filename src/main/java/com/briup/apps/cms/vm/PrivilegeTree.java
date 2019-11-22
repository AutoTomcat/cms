package com.briup.apps.cms.vm;

import java.util.List;

import com.briup.apps.cms.bean.Privilege;

public class PrivilegeTree {
	
	private List<Privilege> children;

    public List<Privilege> getChildren() {
        return children;
    }

    public void setChildren(List<Privilege> children) {
        this.children = children;
    }
}
