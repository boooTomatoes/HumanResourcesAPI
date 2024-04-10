import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import persistence.dto.ProjectDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectControllerTest {

    @Test
    public void testGetAllProjects() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target("http://localhost:9191/HumanResourcesAPI/webapi/projects").request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetProjectById() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target("http://localhost:9191/HumanResourcesAPI/webapi/projects/{id}")
                .resolveTemplate("id", 1)
                .request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testCreateProject() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target("http://localhost:9191/HumanResourcesAPI/webapi/projects").request(MediaType.APPLICATION_JSON);

        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setName("Project X");

        Entity<ProjectDTO> projectEntity = Entity.entity(projectDTO, MediaType.APPLICATION_JSON);

        // Act
        Response response = request.post(projectEntity);

        // Assert
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
    }

    @Test
    public void testUpdateProject() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target("http://localhost:9191/HumanResourcesAPI/webapi/projects/{id}")
                .resolveTemplate("id", 1)
                .request(MediaType.APPLICATION_JSON);

        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setName("Project Y");

        Entity<ProjectDTO> projectEntity = Entity.entity(projectDTO, MediaType.APPLICATION_JSON);

        // Act
        Response response = request.put(projectEntity);

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testDeleteProject() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target("http://localhost:9191/HumanResourcesAPI/webapi/projects/{id}")
                .resolveTemplate("id", 1)
                .request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.delete();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}