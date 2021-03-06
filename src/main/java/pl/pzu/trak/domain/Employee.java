package pl.pzu.trak.domain;

import java.util.Collection;


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
    private Long id_employee;
    private String first_name;
    private String last_name;
    private String team;
    private String workplace;
    private boolean employee_status;
    
    @OneToMany(mappedBy="employee")
    Collection<EmployeeContract> employeeContract;
    @OneToMany(mappedBy="employee")
    Collection<EmployeeSystems> employeeSystems;
    

    
	public Employee(Long id_employee, String first_name, String last_name, String team, String workplace,
			boolean employee_status, Collection<EmployeeContract> employeeContract, Collection<EmployeeSystems> employeeSystems) {
		super();
		this.id_employee = id_employee;
		this.first_name = first_name;
		this.last_name = last_name;
		this.team = team;
		this.workplace = workplace;
		this.employee_status = employee_status;
		this.employeeContract = employeeContract;
		this.employeeSystems = employeeSystems;
	}
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId_employee() {
		return id_employee;
	}
	public void setId_employee(Long id_employee) {
		this.id_employee = id_employee;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getWorkplace() {
		return workplace;
	}
	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}
	public boolean isEmployee_status() {
		return employee_status;
	}
	public void setEmployee_status(boolean employee_status) {
		this.employee_status = employee_status;
	}
	public Collection<EmployeeContract> getEmployeeContract() {
		return employeeContract;
	}
	public void setEmployeeContract(Collection<EmployeeContract> employeeContract) {
		this.employeeContract = employeeContract;
	}
	public Collection<EmployeeSystems> getEmployeeSystems() {
		return employeeSystems;
	}
	public void setEmployeeSystems(Collection<EmployeeSystems> employeeSystems) {
		this.employeeSystems = employeeSystems;
	}
	@Override
	public String toString() {
		return "Employee [id_employee=" + id_employee + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", team=" + team + ", workplace=" + workplace + ", employee_status=" + employee_status
				+ ", employeeContract=" + employeeContract + ", employeeSystems=" + employeeSystems + "]";
	}
	
	
}

