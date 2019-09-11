package pl.pzu.trak.domain;

import java.util.List;

public class PrivilegeListDto
{
	private List<Privilege> privileges;
	
	public void addPrivilege(Privilege privilege)
	{
		this.privileges.add(privilege);
	}

	public List<Privilege> getPrivileges()
	{
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges)
	{
		this.privileges = privileges;
	}
	
}
