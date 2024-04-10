package persistence.repository;

import jakarta.persistence.EntityManager;
import persistence.entities.Employee;
import persistence.entities.Project;

import java.util.List;

public class ProjectRepository extends GenericRepository<Project, Long>{
    private ProjectRepository(Class<Project> entityClass) {
        super(entityClass);
    }

    private static final ProjectRepository INSTANCE = new ProjectRepository(Project.class);

    public static ProjectRepository getInstance(){
        return INSTANCE;
    }


    public List<Employee> getEmployeesByProjectID(Long id, EntityManager entityManager) {
        return entityManager.createQuery("SELECT e FROM Project p JOIN p.employees e WHERE p.id = :id", Employee.class)
                .setParameter("id", id)
                .getResultList();
    }
}
