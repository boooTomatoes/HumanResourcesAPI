package controllers.rest;

import controllers.rest.exceptions.mappers.ErrorMessage;
import enums.VacationStatus;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import persistence.dto.VacationDTO;
import service.VacationService;

@Path("/vacations")
public class VacationController {

    @POST
    @Path("/{id}")
    public Response requestVacation(@PathParam("id") Long id, VacationDTO vacationDTO) {
        VacationDTO requestVacation = new VacationDTO();
        requestVacation.setEmployeeId(id);
        requestVacation.setStartDate(vacationDTO.getStartDate());
        requestVacation.setEndDate(vacationDTO.getEndDate());
        requestVacation.setStatus(VacationStatus.PENDING);
        requestVacation.setType(vacationDTO.getType());

        if(VacationService.getInstance().requestVacation(requestVacation)) {
            return Response.ok().entity("Your vacation request was submitted and is pending").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorMessage("You already have a pending vacation request! , wait for its status to change",400,"There is a pending vacation request for this employee")).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getVacationsByEmployeeId(@PathParam("id") Long id) {
        return Response.ok(VacationService.getInstance().findVacationsByEmployeeId(id)).build();
    }

}
