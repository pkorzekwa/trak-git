package pl.pzu.trak.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sl_system")
public class Syst {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private short systemId;

	@Column(name="nazwa", nullable = false, unique = true, columnDefinition = "NCHAR(10)")	
	private String systemName;

	public short getSystemId()
	{
		return systemId;
	}

	public void setSystemId(short systemId)
	{
		this.systemId = systemId;
	}

	public String getSystemName()
	{
		return systemName;
	}

	public void setSystemName(String systemName)
	{
		this.systemName = systemName;
	}	
}
