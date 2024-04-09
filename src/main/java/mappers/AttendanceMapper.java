package mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import persistence.dto.AttendanceDTO;
import persistence.entities.AttendanceSheet;
@Mapper
public interface AttendanceMapper extends BaseMapper<AttendanceSheet, AttendanceDTO>{
    AttendanceMapper INSTANCE = Mappers.getMapper(AttendanceMapper.class);
}
