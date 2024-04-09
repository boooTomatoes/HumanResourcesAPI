package controllers.rest;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.AttendanceService;

import java.util.Base64;
import java.util.Date;

@Path("/attendance")
public class AttendanceController {

    @POST
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response registerAttendance(@Context HttpHeaders headers, @PathParam("id") Long id) {
        String authorizationHeader = headers.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Basic")) {
            if (checkAuthorization(authorizationHeader, id)) {
                if (AttendanceService.getInstance().registerAttendance(id)) {
                    return Response.ok("Attendance registered!").build();
                } else {
                    return Response.status(Response.Status.BAD_REQUEST)
                            .entity("Attendance already registered!").build();
                }
            } else {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("Invalid credentials!").build();
            }
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Authentication required!").build();
        }

    }

    @GET
    @Path("/{id}")
    public Response getAttendance(@PathParam("id") Long id,@QueryParam("date") String date,@QueryParam("offset") Integer offset,@QueryParam("limit") Integer limit) {
        if(date == null || date.isEmpty()){
            if(offset==null)
                offset=0;
            if(limit==null)
                limit=10;
            return Response.ok().entity(AttendanceService.getInstance().getAttendance(id,offset,limit)).build();
        }
        else return Response.ok().entity(AttendanceService.getInstance().getAttendance(id,date)).build();

    }



    private boolean checkAuthorization(String authorizationHeader, Long id) {

        String base64Credentials = authorizationHeader.substring("Basic".length()).trim();
        String credentials = new String(Base64.getDecoder().decode(base64Credentials));
        final String[] values = credentials.split(":", 2);
        return AttendanceService.getInstance().checkAuthentication(values[0], values[1], id);
    }


}
