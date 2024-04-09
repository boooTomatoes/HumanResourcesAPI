package persistence.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDTO extends BaseDTO {
    private Long id;
    private Long employeeId;
    private Date date;
    private Date checkInTime;
    private Date checkOutTime;
}