package controllers.soap;

import jakarta.jws.WebService;


import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.BindingType;
import persistence.dto.EmployeeDTO;
import persistence.dto.JobDTO;
import service.JobService;

import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
@BindingType(value = jakarta.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public class JobController {

    @WebMethod
    public JobDTO[] getJobs() {
        return JobService.getInstance().findAll().toArray(new JobDTO[0]);
    }

    @WebMethod
    public JobDTO getJobById(@WebParam(name = "id") Long id) {
        return JobService.getInstance().findById(id);
    }

    @WebMethod
    public List<EmployeeDTO> getEmployeesByJobId(@WebParam(name = "id") Long id) {
        return JobService.getInstance().findEmployeesByJobId(id);
    }

    @WebMethod
    public String createJob(@WebParam(name = "jobDTO") JobDTO jobDTO) {
        if(JobService.getInstance().save(jobDTO)) {
            return "Job created!";
        } else {
            return "Job already exists!";
        }
    }

    @WebMethod
    public String addEmployeeToJob(@WebParam(name = "id") Long id, @WebParam(name = "employeeId") Long employeeId) {
        if(JobService.getInstance().addEmployeeToJob(id, employeeId)) {
            return "Employee added to job!";
        } else {
            return "Employee already in job!";
        }
    }

    @WebMethod
    public String updateJob(@WebParam(name = "id") Long id, @WebParam(name = "jobDTO") JobDTO jobDTO) {
        jobDTO.setId(id);
        if(JobService.getInstance().update(jobDTO)) {
            return "Job updated!";
        } else {
            return "Job does not exist!";
        }
    }
}
