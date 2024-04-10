import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AttendanceControllerTest {

    @Test
    public void testRegisterAttendance() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target("http://localhost:9191/HumanResourcesAPI/webapi/attendance/{id}")
                .resolveTemplate("id", 8)
                .request(MediaType.TEXT_PLAIN)
                .header("Authorization", "Basic bmFkYToxMjM=");

        // Act
        Response response = request.post(null); // No entity needed for this request

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetAttendance() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target("http://localhost:9191/HumanResourcesAPI/webapi/attendance/{id}")
                .resolveTemplate("id", 8)
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic bmFkYToxMjM=");

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}