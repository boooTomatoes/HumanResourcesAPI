package persistence.entities;


import jakarta.persistence.*;
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
    private String phone;
    private BigDecimal salary;

    @Embedded
    private Address address;

    @ManyToOne
    private Employee manager;

    @OneToMany(mappedBy = "manager",fetch = FetchType.LAZY)
    private Set<Employee> employees= new HashSet<>();

    @OneToOne(mappedBy = "manager")
    private Department managedDepartment;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    @OneToMany(mappedBy = "employee",fetch = FetchType.LAZY)
    private Set<Vacation> vacations= new HashSet<>();

    @ManyToOne
    private Job job;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "employee_project",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private Set<Project> projects = new HashSet<>();

    @Transient
    private String departmentName;
    @Transient
    private String managerName;
    private boolean currentlyEmployed;

    @PostLoad
    public void postLoad() {
        if (department != null) {
            departmentName = department.getName();
        }
        if (manager != null) {
            managerName = manager.getName();
        }
    }







}
