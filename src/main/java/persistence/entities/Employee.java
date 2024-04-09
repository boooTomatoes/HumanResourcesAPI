package persistence.entities;


import jakarta.persistence.*;
import jakarta.ws.rs.DefaultValue;
import lombok.*;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String username;
    private String phone;
    private BigDecimal salary;

    @Embedded
    private Address address;

    @ManyToOne
    private Employee manager;

    @OneToMany(mappedBy = "manager")
    private Set<Employee> employees= new HashSet<>();

    @OneToOne
    private Department managedDepartment;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    @OneToMany(mappedBy = "employee")
    private Set<Vacation> vacations= new HashSet<>();

    @ManyToOne
    private Job job;

    @ManyToMany
    @JoinTable(
            name = "employee_project",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private Set<Project> projects = new HashSet<>();

    @DefaultValue("true")
    private boolean currentlyEmployed;

}
