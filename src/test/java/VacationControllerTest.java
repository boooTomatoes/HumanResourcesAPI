import enums.VacationType;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import persistence.dto.VacationDTO;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VacationControllerTest {

    @Test
    public void testGetAllVacations() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target("http://localhost:9191/HumanResourcesAPI/webapi/vacations")
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic bmFkYToxMjM=");

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetVacationsByEmployeeId() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target("http://localhost:9191/HumanResourcesAPI/webapi/vacations/employee/{id}")
                .resolveTemplate("id", 1)
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic bmFkYToxMjM=");

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testRequestVacation() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target("http://localhost:9191/HumanResourcesAPI/webapi/vacations/{id}")
                .resolveTemplate("id", 1)
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic bmFkYToxMjM=");

        VacationDTO vacationDTO = new VacationDTO();
        vacationDTO.setStartDate(LocalDate.parse("2022-01-01"));
        vacationDTO.setEndDate(LocalDate.parse("2022-01-03"));

        Entity<VacationDTO> vacationEntity = Entity.entity(vacationDTO, MediaType.APPLICATION_JSON);

        // Act
        Response response = request.post(vacationEntity);

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }


    @Test
    public void testUpdateVacationStatus() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target("http://localhost:9191/HumanResourcesAPI/webapi/vacations/{id}/status")
                .resolveTemplate("id", 1)
                .queryParam("status", "APPROVED")
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic bmFkYToxMjM=");

        // Act
        Entity<String> entity = Entity.entity("", MediaType.TEXT_PLAIN);
        Response response = request.put(entity); // No entity needed for this request

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}