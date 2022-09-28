package dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link entities.CityInfo} entity
 */
public class CityInfoDto implements Serializable {
    private final Integer id;
    @Size(max = 45)
    @NotNull
    private final String zipcode;
    @Size(max = 45)
    @NotNull
    private final String city;
    private final Set<AddressDto> addresses;

    public CityInfoDto(Integer id, String zipcode, String city, Set<AddressDto> addresses) {
        this.id = id;
        this.zipcode = zipcode;
        this.city = city;
        this.addresses = addresses;
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

    public Set<AddressDto> getAddresses() {
        return addresses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityInfoDto entity = (CityInfoDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.zipcode, entity.zipcode) &&
                Objects.equals(this.city, entity.city) &&
                Objects.equals(this.addresses, entity.addresses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, zipcode, city, addresses);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "zipcode = " + zipcode + ", " +
                "city = " + city + ", " +
                "addresses = " + addresses + ")";
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
            if (o == null || getClass() != o.getClass()) return false;
            AddressDto entity = (AddressDto) o;
            return Objects.equals(this.id, entity.id) &&
                    Objects.equals(this.street, entity.street) &&
                    Objects.equals(this.additionalInfo, entity.additionalInfo);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, street, additionalInfo);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "street = " + street + ", " +
                    "additionalInfo = " + additionalInfo + ")";
        }
    }
}