
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
public class Company implements Serializable {
    private String bs;
    private String catchPhrase;
    private String companyName;
}
