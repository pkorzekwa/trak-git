package pl.pzu.trak.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sl_jednostka")
public class UnitDictionary {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Short unitId;
	
	@Column(name="nazwa", nullable = false, unique = true, columnDefinition = "NVARCHAR(50)")	
	private String unitName;

}
