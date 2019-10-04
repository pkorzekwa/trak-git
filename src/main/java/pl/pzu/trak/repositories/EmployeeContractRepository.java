package pl.pzu.trak.repositories;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.pzu.trak.domain.EmployeeContract;
import pl.pzu.trak.domain.EmployeeSystems;

@Repository
public interface  EmployeeContractRepository  extends JpaRepository<EmployeeContract, Long>{
	
	
	@Query(value = "SELECT * FROM employee_contract "
			+ " JOIN employee_type_of_contract_dictionary ON employee_contract.id_type_of_contract = employee_type_of_contract_dictionary.id_type_of_contract WHERE id_employee = :id_employee", nativeQuery = true)
	Collection<EmployeeContract> employeeContractsListContracts(@Param("id_employee") Long id_employee);
	
	
	@Transactional
	@Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM employee_contract WHERE id_contract = :id_contract", nativeQuery = true)
    void deleteById(@Param("id_contract") Long id_contract);
	
	@Query("SELECT cd FROM EmployeeContract c JOIN c.employeeCompanyDictionary cd WHERE c.id_employee = :id_employee")
	Collection<EmployeeContract> employeeDetailsCompanyById (@Param("id_employee") Long id_employee);

}
//SELECT * from employee_type_of_contract_dictionary LEFT JOIN employee_contract ON employee_contract.id_type_of_contract = employee_type_of_contract_dictionary.id_type_of_contract WHERE id_employee = 2