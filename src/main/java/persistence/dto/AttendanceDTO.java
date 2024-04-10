package persistence.dto;


import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import persistence.dto.adapters.InstantAdapter;
import persistence.dto.adapters.LocalDateAdapter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType.FIELD)
public class AttendanceDTO extends BaseDTO {
    private Long id;
    private Long employeeId;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate date;
    @XmlJavaTypeAdapter(InstantAdapter.class)
    private Instant checkInTime;
    @XmlJavaTypeAdapter(InstantAdapter.class)
    private Instant checkOutTime;
}