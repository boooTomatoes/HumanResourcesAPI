package persistence.repository;
import jakarta.persistence.EntityManager;
import persistence.entities.Employee;
import persistence.entities.Project;

import java.util.List;
import java.util.Set;


public class EmployeeRepository extends GenericRepository<Employee, Long>{
    private static final EmployeeRepository INSTANCE = new EmployeeRepository();
    private EmployeeRepository() {
        super(Employee.class);
    }

    public static EmployeeRepository getInstance() {
        return INSTANCE;
    }

    public Set<Project> findProjectsByEmployeeId(Long employeeId, EntityManager entityManager) {
        Employee employee = this.findById(employeeId, entityManager);
        return employee.getProjects();
    }

}
