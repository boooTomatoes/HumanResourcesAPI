package persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO extends BaseDTO{
    private Long id;
    private String name;
    private String email;
    private String phone;
    private BigDecimal salary;
    private AddressDTO address;
    private Long managerId;
    private String managerName;
    private Long departmentId;
    private String departmentName;
    private Long managedDepartmentId; // assuming you want to expose the ID of the managed department
    private Long jobId;
}
