package persistence.repository;

import persistence.entities.Job;

public class JobRepository extends GenericRepository<Job, Long>{
    public JobRepository(Class<Job> entityClass) {
        super(entityClass);
    }


}
