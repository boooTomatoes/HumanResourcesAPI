package service;


import mappers.DepartmentMapper;
import mappers.EmployeeMapper;
import persistence.dto.DepartmentDTO;
import persistence.dto.EmployeeDTO;
import persistence.entities.Department;
import persistence.entities.Employee;
import persistence.repository.DepartmentRepository;

import persistence.util.TransactionUtil;

import java.util.ArrayList;
import java.util.List;

public class DepartmentService extends BaseService<Department, DepartmentDTO,Long> {
    private DepartmentService() {
        super(DepartmentRepository.getInstance(), DepartmentMapper.INSTANCE);
    }

    private static final DepartmentService INSTANCE = new DepartmentService();

    public static DepartmentService getInstance() {
        return INSTANCE;
    }


    public List<EmployeeDTO> findEmployeesByDepartmentId(Long id) {
       return TransactionUtil.doInTransaction(entityManager -> {
            Department department = DepartmentRepository.getInstance().findById(id, entityManager);
            List<Employee> employees = new ArrayList<>(department.getEmployees());
            return List.copyOf(EmployeeMapper.INSTANCE.collectionToDto(employees));
        });

    }

    public EmployeeDTO findManagerByDepartmentId(Long id) {
        return TransactionUtil.doInTransaction(entityManager -> {
            Department department = DepartmentRepository.getInstance().findById(id, entityManager);
            return EmployeeMapper.INSTANCE.toDTO(department.getManager());
        });
    }
}
