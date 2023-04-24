
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
public class Geo implements Serializable {
    private String lat;
    private String lng;
}
