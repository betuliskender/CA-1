package entities;

import dtos.CityInfoDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "city_info")
public class CityInfo {
    public CityInfo() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 45)
    @NotNull
    @Column(name = "zipcode", nullable = false, length = 45)
    private String zipcode;

    @Size(max = 45)
    @NotNull
    @Column(name = "city", nullable = false, length = 45)
    private String city;

    @OneToMany(mappedBy = "cityInfo")
    private Set<Address> addresses = new LinkedHashSet<>();

    public CityInfo(String zipcode, String city) {
        this.zipcode = zipcode;
        this.city = city;
    }


    public  CityInfo(CityInfoDto cityInfoDto){
        this.zipcode = cityInfoDto.getZipcode();
        this.city = cityInfoDto.getCity();
        cityInfoDto.getAddresses().forEach( addressDto -> {
            addresses.add(new Address(addressDto));
        });
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CityInfo)) return false;
        CityInfo cityInfo = (CityInfo) o;
        return getId().equals(cityInfo.getId());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

}