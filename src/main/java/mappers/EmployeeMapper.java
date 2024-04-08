package mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import persistence.dto.EmployeeDTO;
import persistence.entities.Employee;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee, EmployeeDTO>{

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

}
