package dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
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
        if (o == null || getClass() != o.getClass()) return false;
        AddressDto entity = (AddressDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.street, entity.street) &&
                Objects.equals(this.additionalInfo, entity.additionalInfo) &&
                Objects.equals(this.people, entity.people);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, additionalInfo, people);
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
            if (o == null || getClass() != o.getClass()) return false;
            PersonDto entity = (PersonDto) o;
            return Objects.equals(this.id, entity.id) &&
                    Objects.equals(this.email, entity.email) &&
                    Objects.equals(this.firstName, entity.firstName) &&
                    Objects.equals(this.lastName, entity.lastName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, email, firstName, lastName);
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
}