package dtos;

import entities.Hobby;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link entities.Hobby} entity
 */
public class HobbyDto implements Serializable {
    private final Integer id;
    @Size(max = 45)
    @NotNull
    private final String name;
    @Size(max = 45)
    @NotNull
    private final String description;
    private final Set<PersonDto> people;

    public HobbyDto(Integer id, String name, String description, Set<PersonDto> people) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.people = people;
    }


    public HobbyDto(Hobby hobby, Set<PersonDto>personDtos){
       this.id = hobby.getId();
       this.name = hobby.getName();
       this.description = hobby.getDescription();
       this.people = personDtos;
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

    public Set<PersonDto> getPeople() {
        return people;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HobbyDto)) return false;
        HobbyDto hobbyDto = (HobbyDto) o;
        return getId().equals(hobbyDto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "description = " + description + ", " +
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
}