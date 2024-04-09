package service;

import mappers.BaseMapper;
import mappers.ProjectMapper;
import persistence.dto.ProjectDTO;
import persistence.entities.Project;
import persistence.repository.GenericRepository;
import persistence.repository.ProjectRepository;

public class ProjectService extends BaseService<Project, ProjectDTO, Long>{
    private ProjectService() {
        super(ProjectRepository.getInstance(), ProjectMapper.INSTANCE);
    }

    private static final ProjectService INSTANCE = new ProjectService();


    public static ProjectService getInstance() {
        return INSTANCE;
    }




}
