package persistence.entities;

import enums.JobTitle;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends BaseEntity {
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

    @OneToMany(mappedBy = "manager")
    private Set<Employee> employees= new HashSet<>();

    @OneToOne(mappedBy = "manager")
    private Department managedDepartment;

    @ManyToOne
    private Department department;

    @OneToMany(mappedBy = "employee")
    private Set<Vacation> vacations= new HashSet<>();

    @ManyToOne
    private Job job;

    @Transient
    private String departmentName;
    @Transient
    private String managerName;

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
