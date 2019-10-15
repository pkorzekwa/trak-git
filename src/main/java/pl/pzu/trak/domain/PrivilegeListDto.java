package pl.pzu.trak.domain;

import java.util.ArrayList;
import java.util.List;

public class PrivilegeListDto
{
	private List<Privilege> privileges = new ArrayList<Privilege>();
		
	
	public PrivilegeListDto(List<Privilege> privileges)
	{
		super();
		this.privileges = privileges;
	}

	public void addPrivilege(Privilege privilege)
	{
		privileges.add(privilege);
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
