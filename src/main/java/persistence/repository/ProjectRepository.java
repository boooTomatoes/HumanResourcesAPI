package persistence.repository;

import persistence.entities.Project;

public class ProjectRepository extends GenericRepository<Project, Long>{
    private ProjectRepository(Class<Project> entityClass) {
        super(entityClass);
    }

    private static final ProjectRepository INSTANCE = new ProjectRepository(Project.class);

    public static ProjectRepository getInstance(){
        return INSTANCE;
    }


}
