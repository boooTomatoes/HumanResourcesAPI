package controllers.rest.exceptions.mappers;

import controllers.rest.exceptions.ResourceNotFoundException;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.core.Response;

@Provider
public class ResourceNotFoundExceptionMapper implements ExceptionMapper<ResourceNotFoundException> {
    @Override
    public Response toResponse(ResourceNotFoundException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), 404, "There is no Resource with this ID");
        return Response.status(Response.Status.NOT_FOUND).entity(errorMessage).build();
    }
}