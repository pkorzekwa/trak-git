package pl.pzu.trak.domain;

public class EmployeeQuery {

		private Long id_employee;
	    private String first_name;
	    private String last_name;
	    private String team;
	    private String workplace;
	    private String name_company;
	    private String name_system;
	    private boolean employee_status;
	    
		public EmployeeQuery(String first_name, String last_name, String team, String workplace, String name_company,
				String name_system)
		{
			super();
			this.first_name = first_name;
			this.last_name = last_name;
			this.team = team;
			this.workplace = workplace;
			this.name_company = name_company;
			this.name_system = name_system;
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

		public String getName_system() {
			return name_system;
		}
		public void setName_system(String name_system) {
			this.name_system = name_system;
		}
		public String getName_company() {
			return name_company;
		}
		public void setName_company(String name_company) {
			this.name_company = name_company;
		}
		public boolean getEmployee_status() {
			return employee_status;
		}
		public void setEmployee_status(boolean employee_status) {
			this.employee_status = employee_status;
		}
		public Long getId_employee() {
			return id_employee;
		}
		public void setId_employee(Long id_employee) {
			this.id_employee = id_employee;
		}
		public EmployeeQuery(Long id_employee, String first_name, String last_name, String team, String workplace,
				String name_company, String name_system, boolean employee_status) {
			super();
			this.id_employee = id_employee;
			this.first_name = first_name;
			this.last_name = last_name;
			this.team = team;
			this.workplace = workplace;
			this.name_company = name_company;
			this.name_system = name_system;
			this.employee_status = employee_status;
		}

}
