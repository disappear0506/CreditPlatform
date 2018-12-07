package code.pattern.impl;

import code.Patttern.AbstractPermission;

public class PermissionProxy implements AbstractPermission {
    private ReaPermission reaPermission = new ReaPermission();
    private int level;
	@Override
	public String Permission() {
		if(level==0) return "error";
		else return reaPermission.Permission();
		
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
 
}
