package pl.pzu.trak.domain;

public class EmployeeSystemsQuery {
	
    private String name_company;
    private String name_system;
    
    
    
    
    
	public String getName_company() {
		return name_company;
	}
	public void setName_company(String name_company) {
		this.name_company = name_company;
	}
	public String getName_system() {
		return name_system;
	}
	public void setName_system(String name_system) {
		this.name_system = name_system;
	}
	
	
	public EmployeeSystemsQuery(String name_company, String name_system) {
		super();
		this.name_company = name_company;
		this.name_system = name_system;
	}

    
    
    
}
