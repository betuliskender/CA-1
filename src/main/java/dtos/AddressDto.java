package dtos;

import entities.Address;
import entities.Person;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link entities.Address} entity
 */
public class AddressDto implements Serializable {
    private final Integer id;
    @Size(max = 45)
    @NotNull
    private final String street;
    @Size(max = 45)
    @NotNull
    private final String additionalInfo;
    private final Set<PersonDto> people;


    public AddressDto(Integer id, String street, String additionalInfo, Set<PersonDto> people) {
        this.id = id;
        this.street = street;
        this.additionalInfo = additionalInfo;
        this.people = people;
    }

    public AddressDto(Address a) {
        this.id = a.getId();
        this.street = a.getStreet();
        this.additionalInfo = a.getAdditionalInfo();

    }

    public AddressDto(Address address, Set<PersonDto> personDtos) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.additionalInfo = address.getAdditionalInfo();
        this.people = personDtos;

    }

    public AddressDto(AddressDto address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.additionalInfo = address.getAdditionalInfo();
    }

    public static List<AddressDto> getDtos(List<Address> addresses) {
        List<AddressDto> addressDtos = new ArrayList();
        addresses.forEach(address -> addressDtos.add(new dtos.AddressDto(address)));
        return addressDtos;
    }

    public Integer getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public Set<PersonDto> getPeople() {
        return people;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddressDto)) return false;
        AddressDto that = (AddressDto) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "street = " + street + ", " +
                "additionalInfo = " + additionalInfo + ", " +
                "people = " + people + ")";
    }

    /**
     * A DTO for the {@link entities.Person} entity
     */
    public static class PersonDto implements Serializable {
        private final Integer id;
        @Size(max = 45)
        @NotNull
        private final String email;
        @Size(max = 45)
        @NotNull
        private final String firstName;
        @Size(max = 45)
        @NotNull
        private final String lastName;

        public PersonDto(Integer id, String email, String firstName, String lastName) {
            this.id = id;
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public Integer getId() {
            return id;
        }

        public String getEmail() {
            return email;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PersonDto)) return false;
            PersonDto personDto = (PersonDto) o;
            return getId().equals(personDto.getId());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getId());
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "email = " + email + ", " +
                    "firstName = " + firstName + ", " +
                    "lastName = " + lastName + ")";
        }
    }

    public static class  InnerCityInfoDto implements Serializable {
        private final Integer id;
        @Size(max = 45)
        @NotNull
        private final String zipcode;
        @Size(max = 45)
        @NotNull
        private final String city;

        public InnerCityInfoDto(Integer id, String zipcode, String city) {
            this.id = id;
            this.zipcode = zipcode;
            this.city = city;
        }

        public Integer getId() {
            return id;
        }

        public String getZipcode() {
            return zipcode;
        }

        public String getCity() {
            return city;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof InnerCityInfoDto)) return false;
            InnerCityInfoDto that = (InnerCityInfoDto) o;
            return getId().equals(that.getId());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getId());
        }

        @Override
        public String toString() {
            return "InnerCityInfoDto{" +
                    "id=" + id +
                    ", zipcode='" + zipcode + '\'' +
                    ", city='" + city + '\'' +
                    '}';
        }
    }

}