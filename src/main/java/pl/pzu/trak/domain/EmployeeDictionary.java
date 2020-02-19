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
@Table(name = "sl_pracownik")
public class EmployeeDictionary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int employeeId;
	
	@Column(name="nazwisko", columnDefinition = "NVARCHAR(255)")	
	private String lastName;
	
	@Column(name="imie", columnDefinition = "NVARCHAR(255)")	
	private String firstName;
	
	@Column(name="loginAD", columnDefinition = "NVARCHAR(50)")	
	private String loginAD;
	
	@Column(name="aktywny", columnDefinition = "NVARCHAR(255)")	
	private String activeUser;
	
	@Column(name="email", columnDefinition = "NVARCHAR(50)")	
	private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="data_od")
    private Date date_from;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="data_do")
    private Date date_to;
    
	@Column(name = "id_SAP")
	private int idSAP;
	
	@Column(name = "zespol")
	private Short teamId;
	
	@Column(name = "jednostka")
	private Short unitId;
	
}
