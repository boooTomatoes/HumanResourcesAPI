package controllers.rest;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import persistence.dto.DepartmentDTO;
import persistence.dto.EmployeeDTO;
import persistence.dto.ProjectDTO;
import persistence.dto.Link;
import service.EmployeeService;
import service.VacationService;


import java.util.*;

@Path("/employees")
public class EmployeeController {
    @Context
    UriInfo uriInfo;

    @GET
    public Response getEmployees(@DefaultValue("0") @QueryParam("offset") Integer offset, @DefaultValue("10") @QueryParam("limit") Integer limit) {
        List<EmployeeDTO> employeeDTOList = EmployeeService.getInstance().getEmployeesWithPagination(offset, limit);
        List<Link> links = createLinks(offset, limit);
        List<Object> response = new ArrayList<>();
        response.add(employeeDTOList);
        response.add(links);
        return Response.ok(response).build();
    }


    @GET
    @Path("/{id}")
    public Response getEmployeeById(@PathParam("id") Long id, @Context UriInfo uriInfo) {
        EmployeeDTO employeeDTO = EmployeeService.getInstance().findById(id);

        String baseUri = uriInfo.getAbsolutePathBuilder().build().toString();

        employeeDTO.setLinks(new ArrayList<>());
        employeeDTO.getLinks().add(new Link("self", baseUri));
        employeeDTO.getLinks().add(new Link("projects", baseUri + "/projects"));
        employeeDTO.getLinks().add(new Link("manager", baseUri + "/manager"));
        employeeDTO.getLinks().add(new Link("department", baseUri + "/department"));

        return Response.status(Response.Status.OK).entity(employeeDTO).build();
    }



    @GET
    @Path("/{id}/projects")
    public Response getProjectsByEmployeeId(@PathParam("id") Long id) {
        Set<ProjectDTO> employeeDTOSet = EmployeeService.getInstance().findProjectsByEmployeeId(id);
        List<ProjectDTO> projectDTOList = new ArrayList<>(employeeDTOSet);
        return Response.ok(projectDTOList).build();
    }

    @GET
    @Path("/{id}/manager")
    public Response getManagerByEmployeeId(@PathParam("id") Long id) {
        EmployeeDTO employeeDTO = EmployeeService.getInstance().findManagerByEmployeeId(id);
        return Response.ok(employeeDTO).build();
    }

    @GET
    @Path("{id}/department")
    public Response getDepartmentByEmployeeId(@PathParam("id") Long id) {
        DepartmentDTO departmentDTO = EmployeeService.getInstance().findDepartmentByEmployeeId(id);
        return Response.ok(departmentDTO).build();
    }

    @GET
    @Path("/{managerId}/employees")
    public Response getEmployeesByManagerId(@PathParam("managerId") Long managerId) {
        List<EmployeeDTO> employeeDTOList = EmployeeService.getInstance().findEmployeesByManagerId(managerId);
        return Response.ok(employeeDTOList).build();
    }

    @GET
    @Path("{id}/vacations")
    public Response getVacationsByEmployeeId(@PathParam("id") Long id, @DefaultValue("0") @QueryParam("offset") Integer offset, @DefaultValue("10") @QueryParam("limit") Integer limit) {
        return Response.ok(VacationService.getInstance().findVacationsByEmployeeId(id, offset, limit)).build();
    }


    @POST
    public Response createEmployee(@Valid EmployeeDTO employeeDTO) {
        EmployeeService.getInstance().save(employeeDTO);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateEmployee(@PathParam("id") Long id, EmployeeDTO employeeDTO) {
        employeeDTO.setId(id);
        EmployeeService.getInstance().update(employeeDTO);
        return Response.ok().build();
    }



    @DELETE
    @Path("/{id}")
    public Response deleteEmployee(@PathParam("id") Long id) {
        EmployeeService.getInstance().deleteFromCurrent(id);
        return Response.ok().build();
    }



    private List<Link> createLinks(Integer offset, Integer limit) {
        String baseUri = uriInfo.getAbsolutePathBuilder().build().toString();
        List<Link> links = new ArrayList<>();
        links.add(new Link("self", baseUri));
        links.add(new Link("next", baseUri + "?offset=" + (offset + limit) + "&limit=" + limit));
        if (offset - limit >= 0) {
            links.add(new Link("prev", baseUri + "?offset=" + (offset - limit) + "&limit=" + limit));
        }
        return links;
    }

}
