package pl.pzu.trak.repositories;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.pzu.trak.domain.EmployeeContract;

@Repository
public interface  EmployeeContractRepository  extends JpaRepository<EmployeeContract, Long>{
	
	
	@Query(value = "SELECT * FROM employee_contract "
			+ " JOIN employee_type_of_contract_dictionary ON employee_contract.id_type_of_contract = employee_type_of_contract_dictionary.id_type_of_contract WHERE id_employee = :id_employee", nativeQuery = true)
	List<EmployeeContract> employeeContractsListContracts(@Param("id_employee") Long id_employee);

}
//SELECT * from employee_type_of_contract_dictionary LEFT JOIN employee_contract ON employee_contract.id_type_of_contract = employee_type_of_contract_dictionary.id_type_of_contract WHERE id_employee = 2