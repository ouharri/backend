package com.ouharri.aftas.model.entities;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Objects;

/**
 * This class represents an address of {@link User} or {@link Competition} entities.
 *
 * <p>The address includes information such as region, district, city, street, building number,
 * apartment number, and postal code.
 *
 * <p>Instances of this class are embedded within {@link User} or {@link Competition} entities.
 *
 * @author Ouharri Outman
 * @version 1.0
 */
@Builder
@Getter
@Setter
@ToString
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    /**
     * The name of the region.
     */
    @Size(max = 30, message = "Region name is too long")
    private String region;

    /**
     * The name of the district.
     */
    @Size(max = 30, message = "District name is too long")
    private String district;

    /**
     * The name of the city.
     */
    @Size(max = 30, message = "City name is too long")
    private String city;

    /**
     * The name of the street.
     */
    @Size(max = 30, message = "Street name is too long")
    private String street;

    /**
     * The building number.
     */
    @Min(value = 1, message = "Building number must be greater than 0")
    @Max(value = 32767, message = "Building number is too big")
    private Integer building;

    /**
     * The apartment number.
     */
    @Min(value = 1, message = "Apartment number must be greater than 0")
    @Max(value = 32767, message = "Apartment number is too big")
    private Integer apartment;

    /**
     * The postal code.
     */
    @Min(value = 1001, message = "Invalid postal code")
    @Max(value = 99999, message = "Invalid postal code")
    private Integer postalCode;

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o The reference object with which to compare.
     * @return {@code true} if this object is the same as the of argument;
     * {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (!(o instanceof Address other))
            return false;
        return Objects.equals(region, other.region) &&
                Objects.equals(district, other.district) &&
                Objects.equals(city, other.city) &&
                Objects.equals(street, other.street) &&
                Objects.equals(building, other.building) &&
                Objects.equals(apartment, other.apartment) &&
                Objects.equals(postalCode, other.postalCode);
    }
}