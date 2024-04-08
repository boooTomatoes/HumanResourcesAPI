package persistence.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    private Date endDate;


    @ManyToMany(mappedBy = "projects")
    private Set<Employee> employees = new HashSet<>();

    @PrePersist
    public void setDefaultStartDate() {
        if (startDate == null) {
            startDate = new Date();
        }
    }
}