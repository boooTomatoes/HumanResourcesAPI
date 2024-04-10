package controllers.soap;

import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.BindingType;
import persistence.dto.DepartmentDTO;
import persistence.dto.EmployeeDTO;
import persistence.dto.ProjectDTO;
import service.EmployeeService;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
@BindingType(value = jakarta.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public class EmployeeController {

    @WebMethod
    public List<EmployeeDTO> getEmployees() {
        return EmployeeService.getInstance().findAllWithEagerFetch();
    }

    @WebMethod
    public EmployeeDTO getEmployeeById(@WebParam(name = "id") Long id) {
        return EmployeeService.getInstance().findById(id);
    }

    @WebMethod
    public List<ProjectDTO> getProjectsByEmployeeId(@WebParam(name = "id") Long id) {
        Set<ProjectDTO> employeeDTOSet = EmployeeService.getInstance().findProjectsByEmployeeId(id);
        return new ArrayList<>(employeeDTOSet);
    }

    @WebMethod
    public EmployeeDTO getManagerByEmployeeId(@WebParam(name = "id") Long id) {
        return EmployeeService.getInstance().findManagerByEmployeeId(id);
    }

    @WebMethod
    public DepartmentDTO getDepartmentByEmployeeId(@WebParam(name = "id") Long id) {
        return EmployeeService.getInstance().findDepartmentByEmployeeId(id);
    }

    @WebMethod
    public List<EmployeeDTO> getEmployeesByManagerId(@WebParam(name = "managerId") Long managerId) {
        return EmployeeService.getInstance().findEmployeesByManagerId(managerId);
    }

    @WebMethod
    public void createEmployee(@WebParam(name = "employeeDTO") EmployeeDTO employeeDTO) {
        EmployeeService.getInstance().save(employeeDTO);
    }

    @WebMethod
    public void updateEmployee(@WebParam(name = "id") Long id, @WebParam(name = "employeeDTO") EmployeeDTO employeeDTO) {
        employeeDTO.setId(id);
        EmployeeService.getInstance().update(employeeDTO);
    }


    @WebMethod
    public void deleteEmployee(@WebParam(name = "id") Long id) {
        EmployeeService.getInstance().deleteFromCurrent(id);
    }

}