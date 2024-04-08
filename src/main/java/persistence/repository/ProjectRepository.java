package persistence.repository;

import persistence.entities.Project;

public class ProjectRepository extends GenericRepository<Project, Long>{
    public ProjectRepository(Class<Project> entityClass) {
        super(entityClass);
    }


}
