package mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import persistence.dto.DepartmentDTO;
import persistence.entities.Department;

@Mapper
public interface DepartmentMapper extends BaseMapper<Department,DepartmentDTO>{
    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);
}
