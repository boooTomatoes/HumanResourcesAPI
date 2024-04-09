package controllers.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import persistence.dto.ProjectDTO;
import service.ProjectService;

import java.util.List;

@Path("/projects")
public class ProjectController {

    @GET
    public Response getProjects() {
        List<ProjectDTO> projectDTOList = ProjectService.getInstance().findAll();
        return Response.ok(projectDTOList).build();
    }

    @GET
    @Path("/{id}")
    public Response getProjectById(Long id) {
        ProjectDTO projectDTO = ProjectService.getInstance().findById(id);
        return Response.ok(projectDTO).build();
    }

    @POST
    public Response createProject(ProjectDTO projectDTO) {
        if(ProjectService.getInstance().save(projectDTO)) {
            return Response.ok().entity("Project created!").build();
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


}
