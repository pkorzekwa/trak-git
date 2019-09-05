package pl.pzu.trak.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "employee_systems_dictionary")
public class EmployeeSystemsDictionary {
	
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id_systems;
	    private String name;
	    
	    @OneToMany(mappedBy="employeeSystemsDictionary")
	    List<EmployeeSystems> employeeSystems;
	    


		public EmployeeSystemsDictionary() {
			super();
			// TODO Auto-generated constructor stub
		}

		public EmployeeSystemsDictionary(Long id_systems, String name, List<EmployeeSystems> employeeSystems) {
			super();
			this.id_systems = id_systems;
			this.name = name;
			this.employeeSystems = employeeSystems;
		}

		public List<EmployeeSystems> getEmployeeSystems() {
			return employeeSystems;
		}
		public void setEmployeeSystems(List<EmployeeSystems> employeeSystems) {
			this.employeeSystems = employeeSystems;
		}
		public Long getId_systems() {
			return id_systems;
		}
		public void setId_systems(Long id_systems) {
			this.id_systems = id_systems;
		}
		public List<EmployeeSystems> getEmployeeSystems2() {
			return employeeSystems;
		}
		public void setEmployeeSystems2(List<EmployeeSystems> employeeSystems) {
			this.employeeSystems = employeeSystems;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

	    
	    
}
