import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import persistence.dto.DepartmentDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepartmentControllerTest {

    @Test
    public void testGetAllDepartments() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target("http://localhost:9191/HumanResourcesAPI/webapi/departments").request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetDepartmentById() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target("http://localhost:9191/HumanResourcesAPI/webapi/departments/{id}")
                .resolveTemplate("id", 1)
                .request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testCreateDepartment() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target("http://localhost:9191/HumanResourcesAPI/webapi/departments").request(MediaType.APPLICATION_JSON);

        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName("Finance");


        Entity<DepartmentDTO> departmentEntity = Entity.entity(departmentDTO, MediaType.APPLICATION_JSON);

        // Act
        Response response = request.post(departmentEntity);

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testUpdateDepartment() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target("http://localhost:9191/HumanResourcesAPI/webapi/departments/{id}")
                .resolveTemplate("id", 1)
                .request(MediaType.APPLICATION_JSON);

        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName("Human Resources");

        Entity<DepartmentDTO> departmentEntity = Entity.entity(departmentDTO, MediaType.APPLICATION_JSON);

        // Act
        Response response = request.put(departmentEntity);

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testDeleteDepartmentWithExistingDependency() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target("http://localhost:9191/HumanResourcesAPI/webapi/departments/{id}")
                .resolveTemplate("id", 1)
                .request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.delete();

        // Assert
        //has dependencies
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }
}