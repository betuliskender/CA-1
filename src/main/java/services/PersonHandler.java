package services;

import dtos.AddressDto;
import dtos.CityInfoDto;
import dtos.PersonDto;
import entities.Address;
import entities.CityInfo;
import entities.Person;

public class PersonHandler {
    public static Person mergeDTOAndEntity(PersonDto personDto, Person person)
    {
        Person updatedPerson = person;

        if(personDto.getEmail() != null && !personDto.getEmail().equals(person.getEmail()))
        {
            updatedPerson.setEmail(personDto.getEmail());
        }

        if(personDto.getFirstName() != null && !personDto.getFirstName().equals(person.getFirstName()))
        {
            updatedPerson.setFirstName(personDto.getFirstName());
        }
        if(personDto.getLastName() != null && !personDto.getLastName().equals(person.getLastName()))
        {
            updatedPerson.setLastName(personDto.getLastName());
        }
        if(personDto.getAddress() != null && !personDto.getAddress().equals(person.getAddress()))
        {
            updatedPerson.setAddress(new Address(personDto.getAddress().getStreet(), personDto.getAddress().getAdditionalInfo(), new CityInfo(personDto.getAddress().getInnerCityInfoDto().getId(), personDto.getAddress().getInnerCityInfoDto().getZipcode(), personDto.getAddress().getInnerCityInfoDto().getCity())));
        }
        if(personDto.getHobbies() != null && !personDto.getHobbies().equals(person.getHobbies()))
        {

        }

        return updatedPerson;
    }
}
