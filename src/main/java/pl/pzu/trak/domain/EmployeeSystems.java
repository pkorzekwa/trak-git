package pl.pzu.trak.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employee_systems")
public class EmployeeSystems {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 	private Long id_employee_systems;
	 	private Long id_systems;
	    private Long id_employee;
	    private Long id_company;
		
		@ManyToOne
		@JoinColumn(name = "id_employee", nullable=false , insertable=false, updatable=false)
		private Employee employee;
		
		//System SL
		@ManyToOne
		@JoinColumn(name = "id_systems", nullable=false , insertable=false, updatable=false)
		private EmployeeSystemsDictionary employeeSystemsDictionary;

		public EmployeeSystemsDictionary getEmployeeSystemsDictionary() {
			return employeeSystemsDictionary;
		}

		public void setEmployeeSystemsDictionary(EmployeeSystemsDictionary employeeSystemsDictionary) {
			this.employeeSystemsDictionary = employeeSystemsDictionary;
		}

		public EmployeeSystems(Long id_employee_systems, Long id_systems, Long id_employee, Long id_company,
				Employee employee) {
			super();
			this.id_employee_systems = id_employee_systems;
			this.id_systems = id_systems;
			this.id_employee = id_employee;
			this.id_company = id_company;
			this.employee = employee;
		}

		public EmployeeSystems() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Long getId_employee_systems() {
			return id_employee_systems;
		}

		public void setId_employee_systems(Long id_employee_systems) {
			this.id_employee_systems = id_employee_systems;
		}

		public Long getId_systems() {
			return id_systems;
		}

		public void setId_systems(Long id_systems) {
			this.id_systems = id_systems;
		}

		public Long getId_employee() {
			return id_employee;
		}

		public void setId_employee(Long id_employee) {
			this.id_employee = id_employee;
		}

		public Long getId_company() {
			return id_company;
		}

		public void setId_company(Long id_company) {
			this.id_company = id_company;
		}

		public Employee getEmployee() {
			return employee;
		}

		public void setEmployee(Employee employee) {
			this.employee = employee;
		}
			
			

	    
	    
}
