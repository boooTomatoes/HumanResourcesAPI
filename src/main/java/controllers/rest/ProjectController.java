package controllers.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import persistence.dto.ProjectDTO;
import service.ProjectService;

import java.util.List;

@Path("/projects")
public class ProjectController {

    @GET
    public Response getProjects(@DefaultValue("0") @QueryParam("offset") Integer offset, @DefaultValue("10") @QueryParam("limit") Integer limit) {
        if(offset == null) offset = 0;
        if(limit == null) limit = 10;
        List<ProjectDTO> projectDTOList = ProjectService.getInstance().findAllWithPagination(offset, limit);
        return Response.ok(projectDTOList).build();
    }

    @GET
    @Path("/{id}")
    public Response getProjectById(@PathParam("id") Long id) {
        ProjectDTO projectDTO = ProjectService.getInstance().findById(id);
        return Response.ok(projectDTO).build();
    }

    @POST
    public Response createProject(ProjectDTO projectDTO) {
        if(ProjectService.getInstance().save(projectDTO)) {
            return Response.status(Response.Status.CREATED).entity("Project created!").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Project already exists!").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateProject(@PathParam("id") Long id, ProjectDTO projectDTO) {
        if(ProjectService.getInstance().update(projectDTO)) {
            return Response.ok().entity("Project updated!").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Project does not exist!").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteProject(@PathParam("id") Long id) {
        if(ProjectService.getInstance().deleteProject(id)) {
            return Response.ok().entity("Project deleted!").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Project does not exist!").build();
        }
    }

    @GET
    @Path("/{id}/employees")
    public Response getEmployeesByProjectId(@PathParam("id") Long id) {
        return Response.ok(ProjectService.getInstance().findEmployeesByProjectId(id)).build();
    }



}
