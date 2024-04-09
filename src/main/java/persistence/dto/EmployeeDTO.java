package persistence.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @NotNull(message = "Name cannot be null")
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    private String name;

    @Size(min = 1, max = 100, message = "Email must be between 1 and 100 characters")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Invalid email format")
    private String email;
    @NotNull(message = "Password cannot be null")
    private String password;
    @NotNull(message = "Username cannot be null")
    private String username;
    private String phone;
    private BigDecimal salary;
    private AddressDTO address;
    private Long managerId;
    private Long departmentId;
    private Long managedDepartmentId; // assuming you want to expose the ID of the managed department
    private Long jobId;
}
