package persistence.repository;

import persistence.entities.Job;

public class JobRepository extends GenericRepository<Job, Long>{
    private JobRepository() {
        super(Job.class);
    }

    private static final JobRepository INSTANCE = new JobRepository();

    public static JobRepository getInstance() {
        return INSTANCE;
    }


}
