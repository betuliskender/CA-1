package services;

import dtos.AddressDto;
import dtos.CityInfoDto;
import dtos.HobbyDto;
import dtos.PersonDto;
import entities.*;

import java.util.HashSet;
import java.util.Set;

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
        Address convertedAddress = addressFromDtoConverter(personDto.getAddress());
        if(convertedAddress != (person.getAddress()))
        {
            updatedPerson.setAddress(convertedAddress);
        }
        Set<Hobby> convertedHobbies = hobbyFromDtoConverter(personDto.getHobbies(), person.getHobbies());
        if(!convertedHobbies.equals(person.getHobbies())) {
            updatedPerson.setHobbies(convertedHobbies);
        }
        Set<Phone> convertedPhones = phoneFromDtoConverter(personDto.getPhones(), person.getPhones());
        if(convertedPhones != null && !convertedPhones.equals(person.getPhones())) {
            updatedPerson.setPhones(convertedPhones);
        }
        return updatedPerson;
    }
    private static Address addressFromDtoConverter(PersonDto.InnerAddressDto innerAddressDto) {
        Address updatedAddress = new Address(innerAddressDto.getStreet(), innerAddressDto.getAdditionalInfo(), new CityInfo(innerAddressDto.getInnerCityInfoDto().getId(),innerAddressDto.getInnerCityInfoDto().getZipcode(),innerAddressDto.getInnerCityInfoDto().getCity()));
        return updatedAddress;
    }

    private static Set<Hobby> hobbyFromDtoConverter(Set<PersonDto.InnerHobbyDto> innerHobbyDtos, Set<Hobby> hobbySet) {
        innerHobbyDtos.forEach(innerHobbyDto -> {
            Hobby updatedHobby = new Hobby(innerHobbyDto.getName(),innerHobbyDto.getDescription());
            hobbySet.add(updatedHobby);
        });
        return hobbySet;
    }
    private static Set<Phone> phoneFromDtoConverter(Set<PersonDto.InnerPhoneDto> innerPhoneDtos, Set<Phone> phoneSet) {
        innerPhoneDtos.forEach(innerPhoneDto -> {
            Phone updatedPhone = new Phone(innerPhoneDto.getNumber(), innerPhoneDto.getDescription());
            phoneSet.add(updatedPhone);
        });
        return phoneSet;
    }
}
