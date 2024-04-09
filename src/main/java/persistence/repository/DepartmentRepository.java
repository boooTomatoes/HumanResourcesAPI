package persistence.repository;

import persistence.entities.Department;

public class DepartmentRepository extends GenericRepository<Department, Long>{
    private DepartmentRepository() {
        super(Department.class);
    }
    private static final DepartmentRepository INSTANCE = new DepartmentRepository();

    public static DepartmentRepository getInstance() {
        return INSTANCE;
    }
}
