package mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import persistence.dto.ProjectDTO;
import persistence.entities.Project;

@Mapper
public interface ProjectMapper extends BaseMapper<Project, ProjectDTO>{
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

}
