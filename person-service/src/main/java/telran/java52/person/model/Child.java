package telran.java52.person.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Child extends Person {
	
	private static final long serialVersionUID = 8727306518053198924L;
	 
	String kindergardenString;

	
	public Child(Integer id, String name, LocalDate birthDate, Address address, String kindergardenString) {
		super(id, name, birthDate, address);
		this.kindergardenString = kindergardenString;
	}
	
	
}
