
package dev.jlkeesh.springadvanced.user;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "users")
public class Users implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Address address;
    private Company company;
    private String email;
    private String name;
    private String phone;
    private String username;
    private String website;
}
