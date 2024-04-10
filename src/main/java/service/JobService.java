package service;

import mappers.BaseMapper;
import mappers.EmployeeMapper;
import mappers.JobMapper;
import persistence.dto.EmployeeDTO;
import persistence.dto.JobDTO;
import persistence.entities.Employee;
import persistence.entities.Job;
import persistence.repository.EmployeeRepository;
import persistence.repository.GenericRepository;
import persistence.repository.JobRepository;
import persistence.util.TransactionUtil;

import java.util.Collection;
import java.util.List;

public class JobService extends BaseService<Job, JobDTO, Long>{
    private JobService() {
        super(JobRepository.getInstance(), JobMapper.INSTANCE);
    }

    private static final JobService INSTANCE = new JobService();

    public static JobService getInstance() {
        return INSTANCE;
    }

    public List<EmployeeDTO> findEmployeesByJobId(Long id) {
        return TransactionUtil.doInTransaction(entityManager -> {
            Job job = JobRepository.getInstance().findById(id, entityManager);
            Collection<EmployeeDTO> employeeDTOList = EmployeeMapper.INSTANCE.collectionToDto(job.getEmployees());
            return List.copyOf(employeeDTOList);
        });
    }

    public boolean addEmployeeToJob(Long id, Long employeeId) {
        return TransactionUtil.doInTransaction(entityManager -> {
            Job job = JobRepository.getInstance().findById(id, entityManager);
            EmployeeDTO employeeDTO = EmployeeMapper.INSTANCE.toDTO(job.getEmployees().stream().filter(employee -> employee.getId().equals(employeeId)).findFirst().orElse(null));
            if(employeeDTO != null) {
                return false;
            }
            Employee employee = EmployeeRepository.getInstance().findById(employeeId, entityManager);
            employee.setJob(job);
            job.getEmployees().add(employee);
            JobRepository.getInstance().update(job, entityManager);
            EmployeeRepository.getInstance().update(employee, entityManager);
            return true;
        });
    }
}
