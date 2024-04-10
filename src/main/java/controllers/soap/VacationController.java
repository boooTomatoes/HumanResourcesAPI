package controllers.soap;


import enums.VacationStatus;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.BindingType;
import persistence.dto.VacationDTO;
import service.VacationService;

import jakarta.jws.WebService;
import java.util.List;

@WebService
@BindingType(value = jakarta.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
@SOAPBinding(style = jakarta.jws.soap.SOAPBinding.Style.DOCUMENT)
public class VacationController {


    @WebMethod
    public List<VacationDTO> getVacations(@WebParam(name="offset") int offset,@WebParam(name="limit") int limit) {
        return VacationService.getInstance().findAllWithPagination(offset, limit);
    }


    public String requestVacation(@WebParam(name="id") Long id, @WebParam(name = "vacationDTO") VacationDTO vacationDTO) {
        VacationDTO requestVacation = new VacationDTO();
        requestVacation.setEmployeeId(id);
        requestVacation.setStartDate(vacationDTO.getStartDate());
        requestVacation.setEndDate(vacationDTO.getEndDate());
        requestVacation.setStatus(VacationStatus.PENDING);
        requestVacation.setType(vacationDTO.getType());

        if (VacationService.getInstance().requestVacation(requestVacation)) {
            return "Your vacation request was submitted and is pending";
        } else {
            return "You already have a pending vacation request! , wait for its status to change";
        }
    }


    public List<VacationDTO> getVacationsByEmployeeId(@WebParam(name="id") Long id, @WebParam(name="offset")int offset, @WebParam(name="limit")int limit) {
        return VacationService.getInstance().findVacationsByEmployeeId(id, offset, limit);
    }


    public String updateVacationStatus(@WebParam(name="id") Long id, @WebParam(name="status") String status,@WebParam(name="authorization") String authorization) {
        if (authorization == null || authorization.isEmpty()) {
            return "Authorization is missing";
        } else if (VacationService.getInstance().checkAuthorization(authorization)) {
            boolean isUpdated = VacationService.getInstance().processVacation(status, id);
            if (isUpdated) {
                return "Vacation status updated successfully";
            } else {
                return "Failed to update vacation status";
            }
        } else {
            return "Invalid credentials";
        }
    }


    public String updateVacation(@WebParam(name="id") Long id,  @WebParam(name = "vacationDTO") VacationDTO vacationDTO) {
        boolean isUpdated = VacationService.getInstance().update(vacationDTO);
        if (isUpdated) {
            return "Vacation updated successfully";
        } else {
            return "Failed to update vacation";
        }
    }
}