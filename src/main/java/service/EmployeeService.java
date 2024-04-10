package service;

import mappers.DepartmentMapper;
import mappers.EmployeeMapper;
import mappers.ProjectMapper;
import persistence.dto.DepartmentDTO;
import persistence.dto.EmployeeDTO;
import persistence.dto.ProjectDTO;
import persistence.entities.Employee;
import persistence.repository.DepartmentRepository;
import persistence.repository.EmployeeRepository;
import persistence.util.TransactionUtil;


import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class EmployeeService extends BaseService<Employee, EmployeeDTO, Long> {
    private static final EmployeeService INSTANCE = new EmployeeService();
    private final EmployeeRepository employeeRepository = EmployeeRepository.getInstance();

    private EmployeeService() {
        super(EmployeeRepository.getInstance(), EmployeeMapper.INSTANCE);
    }

    public static EmployeeService getInstance() {
        return INSTANCE;
    }


    public Set<ProjectDTO> findProjectsByEmployeeId(Long employeeId) {
        return TransactionUtil.doInTransaction(entityManager -> {
            Collection<ProjectDTO> projectDTOS = ProjectMapper.INSTANCE.collectionToDto(employeeRepository.findProjectsByEmployeeId(employeeId, entityManager));
            return new HashSet<>(projectDTOS);
        });
    }

    @Override
    public boolean update(EmployeeDTO dto) {
        return TransactionUtil.doInTransaction(entityManager -> {
            Employee entity = EmployeeMapper.INSTANCE.partialUpdate(dto, employeeRepository.findById(dto.getId(), entityManager));
            if (entity == null) {
                return false;
            }
            if (dto.getDepartmentId() != null) {
                entity.setDepartment(DepartmentRepository.getInstance().findById(dto.getDepartmentId(), entityManager));
            }
            if (dto.getManagerId() != null) {
                entity.setManager(employeeRepository.findById(dto.getManagerId(), entityManager));
            }
            return EmployeeRepository.getInstance().update(entity, entityManager);
        });
    }


    public EmployeeDTO findManagerByEmployeeId(Long id) {
        return TransactionUtil.doInTransaction(entityManager -> {
            Employee manager = employeeRepository.findManagerByEmployeeId(id, entityManager);
            return EmployeeMapper.INSTANCE.toDTO(manager);
        });
    }

    public DepartmentDTO findDepartmentByEmployeeId(Long id) {
        return TransactionUtil.doInTransaction(entityManager -> {
            Employee employee = employeeRepository.findById(id, entityManager);
            return DepartmentMapper.INSTANCE.toDTO(employee.getDepartment());
        });
    }

    public List<EmployeeDTO> findEmployeesByManagerId(Long managerId) {
        return TransactionUtil.doInTransaction(entityManager -> {
            Collection<EmployeeDTO> employeeDTOS = EmployeeMapper.INSTANCE.collectionToDto(employeeRepository.findEmployeesByManagerId(managerId, entityManager));
            return List.copyOf(employeeDTOS);
        });
    }

    public List<EmployeeDTO> findAllWithEagerFetch() {
        return TransactionUtil.doInTransaction(entityManager -> {
            Collection<EmployeeDTO> employeeDTOS = EmployeeMapper.INSTANCE.collectionToDto(employeeRepository.findAllWithEagerFetch(entityManager));
            return List.copyOf(employeeDTOS);
        });
    }

    public List<EmployeeDTO> getEmployeesWithPagination(int offset, int limit) {
        return TransactionUtil.doInTransaction(entityManager -> {
            Collection<EmployeeDTO> employeeDTOS = EmployeeMapper.INSTANCE.collectionToDto(employeeRepository.getEmployeesWithPagination(offset, limit, entityManager));
            return List.copyOf(employeeDTOS);
        });
    }

    public boolean deleteFromCurrent(Long id) {
       return TransactionUtil.doInTransaction(entityManager -> {
            Employee employee =employeeRepository.findById(id, entityManager);
            employee.setCurrentlyEmployed(false);
            return employeeRepository.update(employee, entityManager);
        });
    }
}
