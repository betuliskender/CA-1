package dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link entities.Phone} entity
 */
public class PhoneDto implements Serializable {
    private final Integer id;
    @NotNull
    private final Integer number;
    @Size(max = 45)
    @NotNull
    private final String description;
    @NotNull
    private final PersonDto person;

    public PhoneDto(Integer id, Integer number, String description, PersonDto person) {
        this.id = id;
        this.number = number;
        this.description = description;
        this.person = person;
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

    public PersonDto getPerson() {
        return person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneDto entity = (PhoneDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.number, entity.number) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.person, entity.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, description, person);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "number = " + number + ", " +
                "description = " + description + ", " +
                "person = " + person + ")";
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