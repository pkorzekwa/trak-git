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
	    @GeneratedValue(strategy = GenerationType.AUTO)
	 	private Long id_systemy_pracownika;
	 	private Long id_systemu;
	    private Long id_pracownika;
	    private Long id_spolki;
		
		@ManyToOne
		@JoinColumn(name = "id_systemy_pracownika", nullable=false , insertable=false, updatable=false)
		private Employee employee;
			
			
		public Employee getEmployee() {
			return employee;
		}
		public void setEmployee(Employee employee) {
			this.employee = employee;
		}
		public Long getId_systemy_pracownika() {
			return id_systemy_pracownika;
		}
		public void setId_systemy_pracownika(Long id_systemy_pracownika) {
			this.id_systemy_pracownika = id_systemy_pracownika;
		}
		public Long getId_systemu() {
			return id_systemu;
		}
		public void setId_systemu(Long id_systemu) {
			this.id_systemu = id_systemu;
		}
		public Long getId_pracownika() {
			return id_pracownika;
		}
		public void setId_pracownika(Long id_pracownika) {
			this.id_pracownika = id_pracownika;
		}
		public Long getId_spolki() {
			return id_spolki;
		}
		public void setId_spolki(Long id_spolki) {
			this.id_spolki = id_spolki;
		}
	    
	    
}
