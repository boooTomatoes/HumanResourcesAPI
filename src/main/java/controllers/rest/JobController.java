package controllers.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import persistence.dto.JobDTO;
import service.JobService;

@Path("/jobs")
public class JobController {

    @GET
    public Response getJobs() {
        return Response.ok(JobService.getInstance().findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response getJobById(@PathParam("id") Long id) {
        return Response.ok(JobService.getInstance().findById(id)).build();
    }

    @GET
    @Path("/{id}/employees")
    public Response getEmployeesByJobId(@PathParam("id") Long id) {
        return Response.ok(JobService.getInstance().findEmployeesByJobId(id)).build();
    }


    @POST
    public Response createJob(JobDTO jobDTO) {
        if(JobService.getInstance().save(jobDTO)) {
            return Response.ok().entity("Job created!").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Job already exists!").build();
        }
    }

    @POST
    @Path("/{id}/employees/{employeeId}")
    public Response addEmployeeToJob(@PathParam("id") Long id, @PathParam("employeeId") Long employeeId) {
        if(JobService.getInstance().addEmployeeToJob(id, employeeId)) {
            return Response.ok().entity("Employee added to job!").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Employee already in job!").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateJob(@PathParam("id") Long id, JobDTO jobDTO) {
        jobDTO.setId(id);
        if(JobService.getInstance().update(jobDTO)) {
            return Response.ok().entity("Job updated!").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Job does not exist!").build();
        }
    }
}
