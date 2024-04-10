package controllers.soap;

import jakarta.jws.HandlerChain;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.BindingType;
import persistence.dto.AttendanceDTO;
import service.AttendanceService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
@BindingType(value = jakarta.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public class AttendanceController {


    @WebMethod
    public String registerAttendance(@WebParam(name = "id")Long id) {
        if (AttendanceService.getInstance().registerAttendance(id)) {
            return "Attendance registered!";
        } else {
            return "Attendance already registered!";
        }
    }

    @WebMethod
    public List<AttendanceDTO> getAttendance(@WebParam(name="id") Long id, @WebParam(name = "date") String date, @WebParam(name="offset") Integer offset, @WebParam(name = "limit") Integer limit) {
        if (date == null || date.isEmpty()) {
            if (offset == null)
                offset = 0;
            if (limit == null)
                limit = 10;
            return AttendanceService.getInstance().getAttendance(id, offset, limit);
        } else return new ArrayList<>((Collection) AttendanceService.getInstance().getAttendance(id, date));
    }

}
