package persistence.entities;


import enums.JobTitle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private JobTitle title;
    private String description;
    private double minSalary;
    private double maxSalary;
    @OneToMany(mappedBy = "job")
    private Set<Employee> employees= new HashSet<>();
}
