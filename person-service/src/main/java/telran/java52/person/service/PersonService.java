package telran.java52.person.service;



import telran.java52.person.dto.AddressDto;
import telran.java52.person.dto.PersonDto;

public interface PersonService {
	Boolean addPerson(PersonDto personDto);
	
	PersonDto findPersonById (Integer id);
	Iterable <PersonDto> findPersonByCity (String city);
	
	Iterable <PersonDto> findPersonByBirthDate(Integer minAge, Integer maxAge);
	PersonDto updateName(Integer id, String name);
	Iterable <PersonDto> findPersonByName (String name);
	PersonDto updateAddress(Integer id, AddressDto addressDto);
	PersonDto deletePerson (Integer id);
}
