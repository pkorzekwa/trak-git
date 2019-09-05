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
    private Long id_contract; 
    private Date data_from;
    private Date data_to;
    private Long id_type_of_contract;
    private boolean contract_status;
    
    
	@ManyToOne
	@JoinColumn(name = "id_employee", nullable=false , insertable=false, updatable=false)
	private Employee employee;
    
	//Spółka SL
	@ManyToOne
	@JoinColumn(name = "id_company", nullable=false , insertable=false, updatable=false)
	private EmployeeCompanyDictionary employeeCompanyDictionary;

	//Rodzaj umowy SL
	@ManyToOne
	@JoinColumn(name = "id_type_of_contract", nullable=false , insertable=false, updatable=false)
	private EmployeeTypeOfContractDictionary employeeTypeOfContractDictionary;

	public EmployeeContract() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeContract(Long id_contract, Date data_from, Date data_to, Long id_type_of_contract,
			boolean contract_status, Employee employee, EmployeeCompanyDictionary employeeCompanyDictionary,
			EmployeeTypeOfContractDictionary employeeTypeOfContractDictionary) {
		super();
		this.id_contract = id_contract;
		this.data_from = data_from;
		this.data_to = data_to;
		this.id_type_of_contract = id_type_of_contract;
		this.contract_status = contract_status;
		this.employee = employee;
		this.employeeCompanyDictionary = employeeCompanyDictionary;
		this.employeeTypeOfContractDictionary = employeeTypeOfContractDictionary;
	}

	public Long getId_contract() {
		return id_contract;
	}

	public void setId_contract(Long id_contract) {
		this.id_contract = id_contract;
	}

	public Date getData_from() {
		return data_from;
	}

	public void setData_from(Date data_from) {
		this.data_from = data_from;
	}

	public Date getData_to() {
		return data_to;
	}

	public void setData_to(Date data_to) {
		this.data_to = data_to;
	}

	public Long getId_type_of_contract() {
		return id_type_of_contract;
	}

	public void setId_type_of_contract(Long id_type_of_contract) {
		this.id_type_of_contract = id_type_of_contract;
	}

	public boolean isContract_status() {
		return contract_status;
	}

	public void setContract_status(boolean contract_status) {
		this.contract_status = contract_status;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public EmployeeCompanyDictionary getEmployeeCompanyDictionary() {
		return employeeCompanyDictionary;
	}

	public void setEmployeeCompanyDictionary(EmployeeCompanyDictionary employeeCompanyDictionary) {
		this.employeeCompanyDictionary = employeeCompanyDictionary;
	}

	public EmployeeTypeOfContractDictionary getEmployeeTypeOfContractDictionary() {
		return employeeTypeOfContractDictionary;
	}

	public void setEmployeeTypeOfContractDictionary(EmployeeTypeOfContractDictionary employeeTypeOfContractDictionary) {
		this.employeeTypeOfContractDictionary = employeeTypeOfContractDictionary;
	}

	@Override
	public String toString() {
		return "EmployeeContract [id_contract=" + id_contract + ", data_from=" + data_from + ", data_to=" + data_to
				+ ", id_type_of_contract=" + id_type_of_contract + ", contract_status=" + contract_status
				+ ", employee=" + employee + ", employeeCompanyDictionary=" + employeeCompanyDictionary
				+ ", employeeTypeOfContractDictionary=" + employeeTypeOfContractDictionary + ", getId_contract()="
				+ getId_contract() + ", getData_from()=" + getData_from() + ", getData_to()=" + getData_to()
				+ ", getId_type_of_contract()=" + getId_type_of_contract() + ", isContract_status()="
				+ isContract_status() + ", getEmployee()=" + getEmployee() + ", getEmployeeCompanyDictionary()="
				+ getEmployeeCompanyDictionary() + ", getEmployeeTypeOfContractDictionary()="
				+ getEmployeeTypeOfContractDictionary() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
}

