package pl.pzu.trak.domain;

import java.util.Collection;
import java.util.List;

public class PrivilegeListDto
{
	private List<Privilege> privileges;
	public void addAllPrivilege(Role roles)
	{
		this.privileges.addAll(roles.getPrivileges());
	}
	
	
	public void addPrivilege(Privilege privilege)
	{
		this.privileges.add(privilege);
	}

	public Collection<Privilege> getPrivileges()
	{
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges)
	{
		this.privileges = privileges;
	}
	
}
