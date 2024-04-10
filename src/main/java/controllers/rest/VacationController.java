package controllers.rest;

import controllers.rest.exceptions.mappers.ErrorMessage;
import enums.VacationStatus;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import persistence.dto.VacationDTO;
import service.VacationService;

@Path("/vacations")
public class VacationController {

    @Context
    UriInfo uriInfo;

    @GET
    public Response getVacations(@DefaultValue("0") @QueryParam("offset") Integer offset, @DefaultValue("10") @QueryParam("limit") Integer limit) {

        return Response.ok(VacationService.getInstance().findAllWithPagination(offset, limit)).build();

    }

    @POST
    @Path("/{id}")
    public Response requestVacation(@PathParam("id") Long id, VacationDTO vacationDTO) {
        VacationDTO requestVacation = new VacationDTO();
        requestVacation.setEmployeeId(id);
        requestVacation.setStartDate(vacationDTO.getStartDate());
        requestVacation.setEndDate(vacationDTO.getEndDate());
        requestVacation.setStatus(VacationStatus.PENDING);
        requestVacation.setType(vacationDTO.getType());

        if (VacationService.getInstance().requestVacation(requestVacation)) {
            return Response.ok().entity("Your vacation request was submitted and is pending").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorMessage("You already have a pending vacation request! , wait for its status to change", 400, "There is a pending vacation request for this employee")).build();
        }
    }

    @GET
    @Path("employee/{id}")
    public Response getVacationsByEmployeeId(@PathParam("id") Long id,@DefaultValue("0") @QueryParam("offset") Integer offset, @DefaultValue("10") @QueryParam("limit") Integer limit) {
        return Response.ok(VacationService.getInstance().findVacationsByEmployeeId(id,offset,limit)).build();
    }


    @PUT
    @Path("/{id}/status")
    public Response updateVacationStatus(@PathParam("id") Long id, @QueryParam("status") String status, @Context HttpHeaders headers) {
        if (headers.getHeaderString(HttpHeaders.AUTHORIZATION) == null || headers.getHeaderString(HttpHeaders.AUTHORIZATION).isEmpty()) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorMessage("Authorization header is missing", 401, "Authorization header is missing")).build();
        } else if (VacationService.getInstance().checkAuthorization(headers.getHeaderString(HttpHeaders.AUTHORIZATION))) {
            boolean isUpdated = VacationService.getInstance().processVacation(status, id);
            if (isUpdated) {
                return Response.ok().entity("Vacation status updated successfully").build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorMessage("Failed to update vacation status", 400, "Failed to update vacation status")).build();
            }
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorMessage("Invalid credentials", 401, "Invalid credentials")).build();
        }
    }


    @PUT
    @Path("/{id}")
    public Response updateVacation(@PathParam("id") Long id, VacationDTO vacationDTO) {
        boolean isUpdated = VacationService.getInstance().update(vacationDTO);
        if (isUpdated) {
            return Response.ok().entity("Vacation updated successfully").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorMessage("Failed to update vacation", 400, "Failed to update vacation")).build();
        }
    }

}
