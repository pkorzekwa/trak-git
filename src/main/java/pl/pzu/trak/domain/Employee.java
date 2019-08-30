package pl.pzu.trak.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pracownika;
    private String imie;
    private String nazwisko;
    private String zespol;
    private String stanowisko;
//    private Long id_umowy;
//    private Long id_systemy_pracownika;
    private boolean status_pracownika;
    
    @OneToMany(mappedBy="employee")
    List<EmployeeContract> employeeContract;
    
    @OneToMany(mappedBy="employee")
    List<EmployeeSystems> employeeSystems;
    
    

	public Employee() {
		super();
	}

	public Employee(Long id_pracownika, String imie, String nazwisko, String zespol, String stanowisko,
			boolean status_pracownika, List<EmployeeContract> employeeContract, List<EmployeeSystems> employeeSystems) {
		super();
		this.id_pracownika = id_pracownika;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.zespol = zespol;
		this.stanowisko = stanowisko;
//		this.id_umowy = id_umowy;
//		this.id_systemy_pracownika = id_systemy_pracownika;
		this.status_pracownika = status_pracownika;
		this.employeeContract = employeeContract;
		this.employeeSystems = employeeSystems;
	}

	public List<EmployeeContract> getEmployeeContract() {
		return employeeContract;
	}

	public void setEmployeeContract(List<EmployeeContract> employeeContract) {
		this.employeeContract = employeeContract;
	}

	public List<EmployeeSystems> getEmployeeSystems() {
		return employeeSystems;
	}

	public void setEmployeeSystems(List<EmployeeSystems> employeeSystems) {
		this.employeeSystems = employeeSystems;
	}

	public Long getId_pracownika() {
		return id_pracownika;
	}

	public void setId_pracownika(Long id_pracownika) {
		this.id_pracownika = id_pracownika;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public String getZespol() {
		return zespol;
	}

	public void setZespol(String zespol) {
		this.zespol = zespol;
	}

	public String getStanowisko() {
		return stanowisko;
	}

	public void setStanowisko(String stanowisko) {
		this.stanowisko = stanowisko;
	}

//	public Long getId_umowy() {
//		return id_umowy;
//	}
//
//	public void setId_umowy(Long id_umowy) {
//		this.id_umowy = id_umowy;
//	}

//	public Long getId_umowy() {
//		return id_umowy;
//	}
//
//	public void setId_umowy(Long id_umowy) {
//		this.id_umowy = id_umowy;
//	}


//	public Long getId_systemy_pracownika() {
//		return id_systemy_pracownika;
//	}
//
//	public void setId_systemy_pracownika(Long id_systemy_pracownika) {
//		this.id_systemy_pracownika = id_systemy_pracownika;
//	}

	public boolean isStatus_pracownika() {
		return status_pracownika;
	}

	public void setStatus_pracownika(boolean status_pracownika) {
		this.status_pracownika = status_pracownika;
	}


    
}
