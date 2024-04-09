package mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Qualifier;
import org.mapstruct.factory.Mappers;
import persistence.dto.VacationDTO;
import persistence.entities.Vacation;

import java.sql.Date;
import java.time.LocalDate;

@Mapper
public interface VacationMapper extends BaseMapper<Vacation,VacationDTO>{
    VacationMapper INSTANCE = Mappers.getMapper(VacationMapper.class);

    @Mapping(target = "startDate", source = "startDate", qualifiedBy = DateToSqlDate.class)
    @Mapping(target = "endDate", source = "endDate", qualifiedBy = DateToSqlDate.class)
    Vacation toEntity(VacationDTO dto);

    @Mapping(target = "startDate", source = "startDate", qualifiedBy = SqlDateToDate.class)
    @Mapping(target = "endDate", source = "endDate", qualifiedBy = SqlDateToDate.class)
    VacationDTO toDTO(Vacation entity);

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
