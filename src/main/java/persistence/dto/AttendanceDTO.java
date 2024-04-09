package persistence.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDTO extends BaseDTO {
    private Long id;
    private Long employeeId;
    private LocalDate date;
    private Instant checkInTime;
    private Instant checkOutTime;
}