package service;

import mappers.BaseMapper;
import persistence.dto.ProjectDTO;
import persistence.entities.Project;
import persistence.repository.GenericRepository;

public class ProjectService extends BaseService<Project, ProjectDTO, Long>{
    public ProjectService(GenericRepository<Project, Long> genericRepository, BaseMapper<Project, ProjectDTO> baseMapper) {
        super(genericRepository, baseMapper);
    }





}
