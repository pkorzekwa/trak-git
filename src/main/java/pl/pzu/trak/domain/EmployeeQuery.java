package pl.pzu.trak.domain;

public class EmployeeQuery {

	    private String imie;
	    private String nazwisko;
	    private String zespol;
	    private String stanowisko;


	    public EmployeeQuery(String imie, String nazwisko, String zespol, String stanowisko) {
			this.imie = imie;
			this.nazwisko = nazwisko;
			this.zespol = zespol;
			this.stanowisko = stanowisko;
	
		}
		public String getImie() {
			return imie;
		}
		public void setImie(String imie) {
			this.imie = imie;
		}
		public String getNazwisko() {
			return nazwisko;
		}
		public void setNazwisko(String nazwisko) {
			this.nazwisko = nazwisko;
		}
		public String getZespol() {
			return zespol;
		}
		public void setZespol(String zespol) {
			this.zespol = zespol;
		}
		public String getStanowisko() {
			return stanowisko;
		}
		public void setStanowisko(String stanowisko) {
			this.stanowisko = stanowisko;
		}

	
	
}
