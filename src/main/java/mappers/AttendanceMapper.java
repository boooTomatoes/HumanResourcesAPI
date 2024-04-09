package mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Qualifier;
import org.mapstruct.factory.Mappers;
import persistence.dto.AttendanceDTO;
import persistence.entities.AttendanceSheet;

import java.sql.Date;
import java.time.LocalDate;

@Mapper
public interface AttendanceMapper extends BaseMapper<AttendanceSheet, AttendanceDTO>{
    AttendanceMapper INSTANCE = Mappers.getMapper(AttendanceMapper.class);
    @Mapping(target = "date", source = "date", qualifiedBy = DateToSqlDate.class)
    AttendanceSheet toEntity(AttendanceDTO dto);

    @Mapping(target = "date", source = "date", qualifiedBy = SqlDateToDate.class)
    AttendanceDTO toDTO(AttendanceSheet entity);

    @Qualifier
    @interface DateToSqlDate {}

    @Qualifier
    @interface SqlDateToDate {}

    @DateToSqlDate
    default Date dateToSqlDate(LocalDate localDate) {
        return Date.valueOf(localDate);
    }

    @SqlDateToDate
    default LocalDate sqlDateToDate(Date date) {
        return date.toLocalDate();
    }
}
