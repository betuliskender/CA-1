package dtos;

import entities.Person;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link entities.Person} entity
 */
public class PersonDto implements Serializable {
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
    @NotNull
    private final AddressDto address;
    private final Set<HobbyDto> hobbies;
    private final Set<PhoneDto> phones;

    public PersonDto(Integer id, String email, String firstName, String lastName, AddressDto address, Set<HobbyDto> hobbies, Set<PhoneDto> phones) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.hobbies = hobbies;
        this.phones = phones;
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

    public AddressDto getAddress() {
        return address;
    }

    public Set<HobbyDto> getHobbies() {
        return hobbies;
    }

    public Set<PhoneDto> getPhones() {
        return phones;
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
                "lastName = " + lastName + ", " +
                "address = " + address + ", " +
                "hobbies = " + hobbies + ", " +
                "phones = " + phones + ")";
    }

    /**
     * A DTO for the {@link entities.Address} entity
     */
    public static class AddressDto implements Serializable {
        private final Integer id;
        @Size(max = 45)
        @NotNull
        private final String street;
        @Size(max = 45)
        @NotNull
        private final String additionalInfo;

        public AddressDto(Integer id, String street, String additionalInfo) {
            this.id = id;
            this.street = street;
            this.additionalInfo = additionalInfo;
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
                    "additionalInfo = " + additionalInfo + ")";
        }
    }

    /**
     * A DTO for the {@link entities.Hobby} entity
     */
    public static class HobbyDto implements Serializable {
        private final Integer id;
        @Size(max = 45)
        @NotNull
        private final String name;
        @Size(max = 45)
        @NotNull
        private final String description;

        public HobbyDto(Integer id, String name, String description) {
            this.id = id;
            this.name = name;
            this.description = description;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            HobbyDto entity = (HobbyDto) o;
            return Objects.equals(this.id, entity.id) &&
                    Objects.equals(this.name, entity.name) &&
                    Objects.equals(this.description, entity.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, description);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "name = " + name + ", " +
                    "description = " + description + ")";
        }
    }

    /**
     * A DTO for the {@link entities.Phone} entity
     */
    public static class PhoneDto implements Serializable {
        private final Integer id;
        @NotNull
        private final Integer number;
        @Size(max = 45)
        @NotNull
        private final String description;

        public PhoneDto(Integer id, Integer number, String description) {
            this.id = id;
            this.number = number;
            this.description = description;
        }

        public Integer getId() {
            return id;
        }

        public Integer getNumber() {
            return number;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PhoneDto entity = (PhoneDto) o;
            return Objects.equals(this.id, entity.id) &&
                    Objects.equals(this.number, entity.number) &&
                    Objects.equals(this.description, entity.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, number, description);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "number = " + number + ", " +
                    "description = " + description + ")";
        }
    }
}