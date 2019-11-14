package pl.pzu.trak.domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "employee_systems_role_dictionary")
public class EmployeeSystemsRoleDictionary {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_systems_role;
	private String name;
	
	
    @OneToMany(mappedBy="employeeSystemsRoleDictionary")
    Collection<EmployeeSystems> employeeSystems;


	public Long getId_systems_role() {
		return id_systems_role;
	}


	public void setId_systems_role(Long id_systems_role) {
		this.id_systems_role = id_systems_role;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Collection<EmployeeSystems> getEmployeeSystems() {
		return employeeSystems;
	}


	public void setEmployeeSystems(Collection<EmployeeSystems> employeeSystems) {
		this.employeeSystems = employeeSystems;
	}


	public EmployeeSystemsRoleDictionary() {
		super();
		// TODO Auto-generated constructor stub
	}


	public EmployeeSystemsRoleDictionary(Long id_systems_role, String name,
			Collection<EmployeeSystems> employeeSystems) {
		super();
		this.id_systems_role = id_systems_role;
		this.name = name;
		this.employeeSystems = employeeSystems;
	}


	@Override
	public String toString() {
		return "EmployeeSystemsRoleDictionary [id_systems_role=" + id_systems_role + ", name=" + name + "]";
	}
    
    
}
