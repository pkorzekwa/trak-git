package pl.pzu.trak.domain;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "employee_type_of_contract_dictionary")
public class EmployeeTypeOfContractDictionary {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_type_of_contract;
    private String name;
    
    @OneToMany(mappedBy="employeeTypeOfContractDictionary")
    Collection<EmployeeContract> employeeContract;
    
    
    


	public EmployeeTypeOfContractDictionary(Long id_type_of_contract, String name, Collection<EmployeeContract> employeeContract) {
		super();
		this.id_type_of_contract = id_type_of_contract;
		this.name = name;
		this.employeeContract = employeeContract;
	}
	
	public EmployeeTypeOfContractDictionary() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId_type_of_contract() {
		return id_type_of_contract;
	}
	public void setId_type_of_contract(Long id_type_of_contract) {
		this.id_type_of_contract = id_type_of_contract;
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
    
}
