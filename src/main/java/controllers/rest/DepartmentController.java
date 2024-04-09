package controllers.rest;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import persistence.dto.DepartmentDTO;
import persistence.dto.EmployeeDTO;
import service.DepartmentService;

import java.util.ArrayList;
import java.util.List;

@Path("/departments")
public class DepartmentController {

    @GET
    public Response getDepartments() {
        List<DepartmentDTO> departmentDTOList = DepartmentService.getInstance().findAll();
        return Response.ok(departmentDTOList).build();
    }


    @GET
    @Path("/{id}")
    public Response getDepartmentById(@PathParam("id") Long id) {
        DepartmentDTO departmentDTO = DepartmentService.getInstance().findById(id);
        return Response.ok(departmentDTO).build();
    }

    @GET
    @Path("/{id}/employees")
    public Response getEmployeesByDepartmentId(@PathParam("id") Long id) {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>(DepartmentService.getInstance().findEmployeesByDepartmentId(id));
        return Response.ok(employeeDTOList).build();
    }

    @GET
    @Path("/{id}/manager")
    public Response getManagerByDepartmentId(@PathParam("id") Long id) {
        EmployeeDTO employeeDTO = DepartmentService.getInstance().findManagerByDepartmentId(id);
        return Response.ok(employeeDTO).build();
    }

    @POST
    public Response createDepartment(@Valid DepartmentDTO departmentDTO) {
        DepartmentService.getInstance().save(departmentDTO);
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}")
    public Response updateDepartment(DepartmentDTO departmentDTO) {
        DepartmentService.getInstance().update(departmentDTO);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteDepartment(@PathParam("id") Long id) {
        DepartmentService.getInstance().delete(id);
        return Response.ok().build();
    }
}
