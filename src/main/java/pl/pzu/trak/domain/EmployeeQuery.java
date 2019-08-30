package pl.pzu.trak.domain;

public class EmployeeQuery {

	    private String imie;
	    private String nazwisko;
	    private String zespol;
	    private String stanowisko;
	    private Long id_spolki;
	    private boolean status_pracownika;


	    public EmployeeQuery(String imie, String nazwisko, String zespol, String stanowisko, Long id_spolki, boolean status_pracownika) {
			this.imie = imie;
			this.nazwisko = nazwisko;
			this.zespol = zespol;
			this.stanowisko = stanowisko;
			this.id_spolki = id_spolki;
			this.status_pracownika = status_pracownika;
			
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
		public Long getId_spolki()
		{
			return id_spolki;
		}
		public void setId_spolki(Long id_spolki)
		{
			this.id_spolki = id_spolki;
		}
		public boolean isStatus_pracownika()
		{
			return status_pracownika;
		}
		public void setStatus_pracownika(boolean status_pracownika)
		{
			this.status_pracownika = status_pracownika;
		}

		
}
