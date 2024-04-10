import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import persistence.dto.EmployeeDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeControllerTest {

    @Test
    public void testGetAllEmployees() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target("http://localhost:9191/HumanResourcesAPI/webapi/employees").request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetEmployeeById() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target("http://localhost:9191/HumanResourcesAPI/webapi/employees/{id}")
                .resolveTemplate("id", 1)
                .request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testCreateEmployee() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target("http://localhost:9191/HumanResourcesAPI/webapi/employees").request(MediaType.APPLICATION_JSON);

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("John Doe");
        employeeDTO.setDepartmentId(1L);

        Entity<EmployeeDTO> employeeEntity = Entity.entity(employeeDTO, MediaType.APPLICATION_JSON);

        // Act
        Response response = request.post(employeeEntity);

        // Assert
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
    }

    @Test
    public void testUpdateEmployee() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target("http://localhost:9191/HumanResourcesAPI/webapi/employees/{id}")
                .resolveTemplate("id", 1)
                .request(MediaType.APPLICATION_JSON);

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("Jane Doe");

        Entity<EmployeeDTO> employeeEntity = Entity.entity(employeeDTO, MediaType.APPLICATION_JSON);

        // Act
        Response response = request.put(employeeEntity);

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testDeleteEmployee() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target("http://localhost:9191/HumanResourcesAPI/webapi/employees/{id}")
                .resolveTemplate("id", 1)
                .request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.delete();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}