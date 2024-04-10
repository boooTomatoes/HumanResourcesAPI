package persistence.entities;


import enums.ProjectStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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
    private LocalDate startDate;
    @Temporal(TemporalType.DATE)
    private LocalDate endDate;
    private ProjectStatus status;


    @ManyToMany(mappedBy = "projects")
    private Set<Employee> employees = new HashSet<>();

    @PrePersist
    public void setDefaultStartDate() {
        if (status == null) {
            status = ProjectStatus.ACTIVE;
        }
    }
}