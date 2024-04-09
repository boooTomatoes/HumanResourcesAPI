package controllers.rest.exceptions.mappers;

import controllers.rest.exceptions.IllegalSaveException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class IllegalSaveExceptionMapper implements ExceptionMapper<IllegalSaveException>{
    @Override
    public Response toResponse(IllegalSaveException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), 400, "Illegal Save");
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorMessage)
                .build();
    }
}
