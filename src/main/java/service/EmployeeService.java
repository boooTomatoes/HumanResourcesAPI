package service;

import mappers.EmployeeMapper;
import mappers.ProjectMapper;
import persistence.dto.EmployeeDTO;
import persistence.dto.ProjectDTO;
import persistence.entities.Employee;
import persistence.repository.DepartmentRepository;
import persistence.repository.EmployeeRepository;
import persistence.util.TransactionUtil;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class EmployeeService extends BaseService<Employee, EmployeeDTO, Long> {
    private static final EmployeeService INSTANCE = new EmployeeService();

    private EmployeeService() {
        super(EmployeeRepository.getInstance(), EmployeeMapper.INSTANCE);
    }

    public static EmployeeService getInstance() {
        return INSTANCE;
    }


    public Set<ProjectDTO> findProjectsByEmployeeId(Long employeeId) {
        return TransactionUtil.doInTransaction(entityManager -> {
            Collection<ProjectDTO> projectDTOS = ProjectMapper.INSTANCE.collectionToDto(EmployeeRepository.getInstance().findProjectsByEmployeeId(employeeId, entityManager));
            return new HashSet<>(projectDTOS);
        });
    }

    @Override
    public boolean update(EmployeeDTO dto) {
        return TransactionUtil.doInTransaction(entityManager -> {
            Employee entity = EmployeeMapper.INSTANCE.partialUpdate(dto, EmployeeRepository.getInstance().findById(dto.getId(), entityManager));
            if (entity == null) {
                return false;
            }
            if (dto.getDepartmentId() != null) {
                entity.setDepartment(DepartmentRepository.getInstance().findById(dto.getDepartmentId(), entityManager));
            }
            if (dto.getManagerId() != null) {
                entity.setManager(EmployeeRepository.getInstance().findById(dto.getManagerId(), entityManager));
            }
            return EmployeeRepository.getInstance().update(entity, entityManager);
        });
    }


}
