package telran.java52.person.service;



import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import telran.java52.person.dao.PersonRepository;
import telran.java52.person.dto.AddressDto;
import telran.java52.person.dto.PersonDto;
import telran.java52.person.dto.exceptions.PersonNotFoundException;
import telran.java52.person.model.Address;
import telran.java52.person.model.Person;


@Service
@RequiredArgsConstructor

public class PersonServiceImpl implements PersonService {

		final PersonRepository personRepository;
		final ModelMapper modelMapper;
	@Transactional
	@Override
	
	public Boolean addPerson(PersonDto personDto) {
		if (personRepository.existsById(personDto.getId())) {
			return false;
		}
		personRepository.save(modelMapper.map(personDto, Person.class));
		return true;
	}

	@Override
	public PersonDto findPersonById(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException:: new);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public Iterable<PersonDto> findPersonByCity(String city) {
		List<Person> person = personRepository.findByAddress_CityIgnoreCase(city);
        return person.stream()
                .map(p -> modelMapper.map(p, PersonDto.class))
                .toList();
		
	}

	@Override
	public PersonDto updateName(Integer id, String name) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException:: new);
		person.setName(name);
		personRepository.save(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public Iterable<PersonDto> findPersonByName(String name) {
		List<Person> person = personRepository.findByNameIgnoreCase(name);
		return person.stream()
					.map(p -> modelMapper.map(p, PersonDto.class))
					.toList();
	}


	@Override
	public PersonDto deletePerson(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException:: new);
		personRepository.delete(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public Iterable<PersonDto> findPersonByBirthDate(Integer minAge, Integer maxAge) {
		LocalDate today = LocalDate.now();
        LocalDate minBirthDate = today.minusYears(maxAge);
        LocalDate maxBirthDate = today.minusYears(minAge);

        List<Person> people = personRepository.findByBirthDateBetween(minBirthDate, maxBirthDate);
        return people.stream()
                .map(p -> modelMapper.map(p, PersonDto.class))
                .toList();
        }
		
	

	@Override
	public PersonDto updateAddress(Integer id, AddressDto addressDto) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException:: new);
        Address newAddress = modelMapper.map(addressDto, Address.class);
        person.setAddress(newAddress);
        personRepository.save(person);
        return modelMapper.map(person, PersonDto.class);
    }
}


