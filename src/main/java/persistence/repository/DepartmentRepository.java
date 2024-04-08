package persistence.repository;

import persistence.entities.Department;

public class DepartmentRepository extends GenericRepository<Department, Long>{
    private DepartmentRepository(Class<Department> entityClass) {
        super(entityClass);
    }
    private static final DepartmentRepository INSTANCE = new DepartmentRepository(Department.class);

    public static DepartmentRepository getInstance() {
        return INSTANCE;
    }
}
