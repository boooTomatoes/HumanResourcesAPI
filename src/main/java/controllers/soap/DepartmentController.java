
package controllers.soap;

import persistence.dto.DepartmentDTO;
import persistence.dto.EmployeeDTO;
import service.DepartmentService;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public class DepartmentController {

    @WebMethod
    public List<DepartmentDTO> getDepartments() {
        return new ArrayList<>(DepartmentService.getInstance().findAll());
    }

    @WebMethod
    public DepartmentDTO getDepartmentById(@WebParam(name = "id") Long id) {
        return DepartmentService.getInstance().findById(id);
    }

    @WebMethod
    public List<EmployeeDTO> getEmployeesByDepartmentId(@WebParam(name = "id") Long id) {
        return new ArrayList<>(DepartmentService.getInstance().findEmployeesByDepartmentId(id));
    }

    @WebMethod
    public EmployeeDTO getManagerByDepartmentId(@WebParam(name = "id") Long id) {
        return DepartmentService.getInstance().findManagerByDepartmentId(id);
    }

    @WebMethod
    public void createDepartment(@WebParam(name = "department") DepartmentDTO departmentDTO) {
        DepartmentService.getInstance().save(departmentDTO);
    }

    @WebMethod
    public void updateDepartment(@WebParam(name = "department") DepartmentDTO departmentDTO) {
        DepartmentService.getInstance().update(departmentDTO);
    }

    // Uncomment the following method if you want to support deleting departments
    // @WebMethod
    // public void deleteDepartment(@WebParam(name = "id") Long id) {
    //     DepartmentService.getInstance().delete(id);
    // }
}