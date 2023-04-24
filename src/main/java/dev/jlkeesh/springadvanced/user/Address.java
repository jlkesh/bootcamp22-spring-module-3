
package dev.jlkeesh.springadvanced.user;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Embeddable
public class Address implements Serializable {

    private String city;
    private Geo geo;
    private String street;
    private String suite;
    private String zipcode;

}
