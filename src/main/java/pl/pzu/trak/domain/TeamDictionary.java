package pl.pzu.trak.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sl_zespol")
public class TeamDictionary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Short teamId;
	
	@Column(name="nazwa_skr", unique = true, columnDefinition = "NVARCHAR(10)")	
	private String shortName;
	
	@Column(name="nazwa", nullable = false, unique = true, columnDefinition = "NVARCHAR(255)")	
	private String teamName;
	
	@Column(name = "jednostka")
	private Short unitId;
}
