package persistence.repository;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import persistence.entities.Employee;
import persistence.entities.Project;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Slf4j
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

    public Employee findManagerByEmployeeId(Long id, EntityManager entityManager) {
        Employee employee = this.findById(id, entityManager);
        return employee.getManager();
    }

    public Collection<Employee> findEmployeesByManagerId(Long managerId, EntityManager entityManager) {
        return entityManager.createQuery("SELECT e FROM Employee e WHERE e.manager.id = :managerId", Employee.class)
                .setParameter("managerId", managerId)
                .getResultList();
    }

    public List<Employee> findAllWithEagerFetch(EntityManager entityManager) {
        try {
            // Construct JPQL query with join fetch for eager loading
            String query =  String.format("SELECT e FROM %s e " +
                    "LEFT JOIN FETCH e.department d " +
                    "LEFT JOIN FETCH e.job j " +
                    "LEFT JOIN FETCH e.manager", Employee.class.getSimpleName());
            return entityManager.createQuery(query,Employee.class).getResultList();
        } catch (Exception e) {
            log.error("Error occurred while fetching entities with eager loading: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    public Employee getEmployee(String username, String password, EntityManager entityManager) {
        return entityManager.createQuery("SELECT e FROM Employee e WHERE e.username = :username AND e.password = :password", Employee.class)
                .setParameter("username", username)
                .setParameter("password", password)
                .getSingleResult();
    }
}
