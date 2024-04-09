package persistence.dto;

import enums.VacationStatus;
import enums.VacationType;
import jakarta.json.bind.annotation.JsonbDateFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VacationDTO extends BaseDTO {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private VacationType type;
    private VacationStatus status;
    private Long employeeId;
}
