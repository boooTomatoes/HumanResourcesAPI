package persistence.entities;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address extends BaseEntity{
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}
