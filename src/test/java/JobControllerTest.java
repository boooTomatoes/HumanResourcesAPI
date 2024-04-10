import enums.JobTitle;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import persistence.dto.JobDTO;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JobControllerTest {

    @Test
    public void testGetAllJobs() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target("http://localhost:9191/HumanResourcesAPI/webapi/jobs").request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetJobById() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target("http://localhost:9191/HumanResourcesAPI/webapi/jobs/{id}")
                .resolveTemplate("id", 1)
                .request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testCreateJob() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target("http://localhost:9191/HumanResourcesAPI/webapi/jobs").request(MediaType.APPLICATION_JSON);

        JobDTO jobDTO = new JobDTO();
        jobDTO.setTitle(JobTitle.FINANCE);
        jobDTO.setMinSalary(1000);
        jobDTO.setMaxSalary(30000);

        Entity<JobDTO> jobEntity = Entity.entity(jobDTO, MediaType.APPLICATION_JSON);

        // Act
        Response response = request.post(jobEntity);

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testAddEmployeeToJob() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target("http://localhost:9191/HumanResourcesAPI/webapi/jobs/{id}/employees/{employeeId}")
                .resolveTemplate("id", 2)
                .resolveTemplate("employeeId", 12)
                .request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.post(null); // No entity needed for this request

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testUpdateJob() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target("http://localhost:9191/HumanResourcesAPI/webapi/jobs/{id}")
                .resolveTemplate("id", 1)
                .request(MediaType.APPLICATION_JSON);

        JobDTO jobDTO = new JobDTO();
        jobDTO.setTitle(JobTitle.DEVELOPER);

        Entity<JobDTO> jobEntity = Entity.entity(jobDTO, MediaType.APPLICATION_JSON);

        // Act
        Response response = request.put(jobEntity);

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}
