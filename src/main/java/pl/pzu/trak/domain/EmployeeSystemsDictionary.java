package pl.pzu.trak.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee_systems_dictionary")
public class EmployeeSystemsDictionary {
	
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id_systems;
	    private String name;
		public Long getId_systems() {
			return id_systems;
		}
		public void setId_systems(Long id_systems) {
			this.id_systems = id_systems;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

	    
	    
}
