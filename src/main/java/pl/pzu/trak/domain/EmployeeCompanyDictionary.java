package pl.pzu.trak.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "employee_company_dictionary")
public class EmployeeCompanyDictionary {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_company;
    private String name;
    
    @OneToMany(mappedBy="employeeCompanyDictionary")
    List<EmployeeContract> employeeContract2;

	public EmployeeCompanyDictionary(Long id_company, String name, List<EmployeeContract> employeeContract2) {
		super();
		this.id_company = id_company;
		this.name = name;
		this.employeeContract2 = employeeContract2;
	}

	public EmployeeCompanyDictionary() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId_company() {
		return id_company;
	}

	public void setId_company(Long id_company) {
		this.id_company = id_company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<EmployeeContract> getEmployeeContract2() {
		return employeeContract2;
	}

	public void setEmployeeContract2(List<EmployeeContract> employeeContract2) {
		this.employeeContract2 = employeeContract2;
	}
    
    


}
