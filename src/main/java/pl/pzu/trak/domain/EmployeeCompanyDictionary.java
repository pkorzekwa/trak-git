package pl.pzu.trak.domain;

import java.util.Collection;


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
    Collection<EmployeeContract> employeeContract;

    @OneToMany(mappedBy="employeeCompanyDictionary")
    Collection<EmployeeSystems> employeeSystems;



	public EmployeeCompanyDictionary(Long id_company, String name, Collection<EmployeeContract> employeeContract, Collection<EmployeeSystems> employeeSystems) {
		super();
		this.id_company = id_company;
		this.name = name;
		this.employeeContract = employeeContract;
		this.employeeSystems = employeeSystems;
	}

	public EmployeeCompanyDictionary() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Collection<EmployeeSystems> getEmployeeSystems() {
		return employeeSystems;
	}

	public void setEmployeeSystems(Collection<EmployeeSystems> employeeSystems) {
		this.employeeSystems = employeeSystems;
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
	public Collection<EmployeeContract> getEmployeeContract() {
		return employeeContract;
	}

	public void setEmployeeContract(Collection<EmployeeContract> employeeContract) {
		this.employeeContract = employeeContract;
	}

	@Override
	public String toString() {
		return "EmployeeCompanyDictionary [id_company=" + id_company + ", name=" + name + "]";
	}



}
