package pl.pzu.trak.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name = "sl_sekcja")
public class SectionDictionary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Short sectionId;
	
	@Column(name="nazwa_skr", unique = true, columnDefinition = "NVARCHAR(10)")	
	private String shortName;
	
	@Column(name="nazwa", nullable = false, unique = true, columnDefinition = "NVARCHAR(255)")	
	private String sectionName;
	
	@Column(name = "id_zespol")
	private Short teamId;
	
	@Column(name="utworzyl", unique = true, columnDefinition = "NCHAR(25)")	
	private String sectionCreated;
	
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="data_utworzenia")
    private Date sectionCreationDate;
    
}
