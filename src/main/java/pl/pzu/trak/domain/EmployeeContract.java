package pl.pzu.trak.domain;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employee_contract")
public class EmployeeContract {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_umowy;  
    private Long id_pracownika;
    private Long id_spolki;
    private Date data_od;
    private Date data_do;
    private Long id_rodzaj_umowy;
    private boolean status_umowy;
    
	@ManyToOne
	@JoinColumn(name = "id_umowy", nullable=false , insertable=false, updatable=false)
	private Employee employee;
    
	
	
	
	public EmployeeContract(Long id_umowy, Long id_pracownika, Long id_spolki, Date data_od, Date data_do, Long id_rodzaj_umowy, boolean status_umowy, Employee employee) {
		super();
		this.id_umowy = id_umowy;
		this.id_pracownika = id_pracownika;
		this.id_spolki = id_spolki;
		this.data_od = data_od;
		this.data_do = data_do;
		this.id_rodzaj_umowy = id_rodzaj_umowy;
		this.status_umowy = status_umowy;
		this.employee = employee;
	}

	public EmployeeContract() {
		super();
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Long getId_umowy() {
		return id_umowy;
	}

	public void setId_umowy(Long id_umowy) {
		this.id_umowy = id_umowy;
	}

	public Long getId_pracownika() {
		return id_pracownika;
	}

	public void setId_pracownika(Long id_pracownika) {
		this.id_pracownika = id_pracownika;
	}

	public Long getId_spolki() {
		return id_spolki;
	}

	public void setId_spolki(Long id_spolki) {
		this.id_spolki = id_spolki;
	}

	public Date getData_od() {
		return data_od;
	}

	public void setData_od(Date data_od) {
		this.data_od = data_od;
	}

	public Date getData_do() {
		return data_do;
	}

	public void setData_do(Date data_do) {
		this.data_do = data_do;
	}

	public Long getId_rodzaj_umowy() {
		return id_rodzaj_umowy;
	}

	public void setId_rodzaj_umowy(Long id_rodzaj_umowy) {
		this.id_rodzaj_umowy = id_rodzaj_umowy;
	}

	public boolean isStatus_umowy() {
		return status_umowy;
	}

	public void setStatus_umowy(boolean status_umowy) {
		this.status_umowy = status_umowy;
	}

    
    

}
