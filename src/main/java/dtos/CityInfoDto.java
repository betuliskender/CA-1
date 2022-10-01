package dtos;

import entities.Address;
import entities.CityInfo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.LinkedHashSet;
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

    public CityInfoDto(CityInfo cityInfo) {
        addresses = new LinkedHashSet<>();
        this.id = cityInfo.getId();
        this.zipcode = cityInfo.getZipcode();
        this.city = cityInfo.getCity();
        cityInfo.getAddresses().forEach(address -> {
            addresses.add(new AddressDto(address));
        });
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
        if (!(o instanceof CityInfoDto)) return false;
        CityInfoDto that = (CityInfoDto) o;
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

        public AddressDto(Address address){
            this.id = address.getId();
            this.street = address.getStreet();
            this.additionalInfo = address.getAdditionalInfo();
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
}