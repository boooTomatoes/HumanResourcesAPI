package controllers.rest.exceptions.mappers;

import controllers.rest.exceptions.IllegalDeleteException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class IllegalDeleteExceptionMapper implements ExceptionMapper<IllegalDeleteException>{
    @Override
    public Response toResponse(IllegalDeleteException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), 417, "a problem occurred while deleting the resource, make sure there are no dependencies on this resource");
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorMessage)
                .build();
    }
}
