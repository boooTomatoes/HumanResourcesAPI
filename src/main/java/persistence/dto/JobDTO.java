package persistence.dto;

import enums.JobTitle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobDTO extends BaseDTO{
    private Long id;
    private JobTitle title;
    private String description;
    private double minSalary;
    private double maxSalary;
}
