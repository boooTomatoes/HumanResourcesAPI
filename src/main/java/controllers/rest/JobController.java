package controllers.rest;

import enums.JobTitle;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/jobs")
public class JobController {

    @GET
    public Response getJobs() {
        return Response.ok(JobTitle.values()).build();
    }

    @GET
    @Path("/{id}")
    public Response getJobById(@PathParam("id") Long id) {
        return Response.ok(JobTitle.values()[id.intValue()]).build();
    }
}
