package controllers.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import persistence.dto.EmployeeDTO;
import persistence.dto.ProjectDTO;
import service.EmployeeService;

import java.util.List;
import java.util.Set;

@Path("/employees")
public class EmployeeController {

    @GET
    public Response getEmployees() {
        List<EmployeeDTO> employeeDTOList = EmployeeService.getInstance().findAll();
        return Response.ok(employeeDTOList).build();
    }

    @GET
    @Path("/{id}")
    public Response getEmployeeById(@PathParam("id") Long id) {
        EmployeeDTO employeeDTO = EmployeeService.getInstance().findById(id);
        return Response.ok(employeeDTO).build();
    }


    @GET
    @Path("/{id}/projects")
    public Response getProjectsByEmployeeId(@PathParam("id") Long id) {
        Set<ProjectDTO> employeeDTOList = EmployeeService.getInstance().findProjectsByEmployeeId(id);
        return Response.ok(employeeDTOList).build();
    }


    @POST
    public Response createEmployee(EmployeeDTO employeeDTO) {
        EmployeeService.getInstance().save(employeeDTO);
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}")
    public Response updateEmployee(@PathParam("id") Long id, EmployeeDTO employeeDTO) {
        employeeDTO.setId(id);
        EmployeeService.getInstance().update(employeeDTO);
        return Response.ok().build();
    }

}
