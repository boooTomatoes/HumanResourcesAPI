package persistence.dto;

import jakarta.json.bind.annotation.JsonbDateFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO extends BaseDTO {
    private Long id;
    private String name;
    private String description;
    @JsonbDateFormat("yyyy-MM-dd")
    private LocalDate startDate;
    @JsonbDateFormat("yyyy-MM-dd")
    private LocalDate endDate;
}
