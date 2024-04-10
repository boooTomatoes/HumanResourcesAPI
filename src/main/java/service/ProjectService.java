package service;

import enums.ProjectStatus;
import mappers.BaseMapper;
import mappers.EmployeeMapper;
import mappers.ProjectMapper;
import persistence.dto.EmployeeDTO;
import persistence.dto.ProjectDTO;
import persistence.entities.Employee;
import persistence.entities.Project;
import persistence.repository.GenericRepository;
import persistence.repository.ProjectRepository;
import persistence.util.TransactionUtil;

import java.util.List;

public class ProjectService extends BaseService<Project, ProjectDTO, Long>{
    private ProjectService() {
        super(ProjectRepository.getInstance(), ProjectMapper.INSTANCE);
    }

    private static final ProjectService INSTANCE = new ProjectService();


    public static ProjectService getInstance() {
        return INSTANCE;
    }


    public List<EmployeeDTO> findEmployeesByProjectId(Long id) {
        return (List<EmployeeDTO>) TransactionUtil.doInTransaction(entityManager -> {
            List<Employee> employees = ProjectRepository.getInstance().getEmployeesByProjectID(id, entityManager);
            return EmployeeMapper.INSTANCE.collectionToDto(employees);
        });
    }

    public boolean deleteProject(Long id) {
        return TransactionUtil.doInTransaction(entityManager -> {
            Project project = ProjectRepository.getInstance().findById(id, entityManager);
            project.setStatus(ProjectStatus.CANCELLED);
            return ProjectRepository.getInstance().update(project, entityManager);
        });
    }
}
