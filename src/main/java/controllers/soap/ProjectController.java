package controllers.soap;

import jakarta.jws.WebService;


import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.BindingType;
import persistence.dto.ProjectDTO;
import service.ProjectService;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
@BindingType(value = jakarta.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public class ProjectController {

    @WebMethod
    public ProjectDTO[] getProjects(@WebParam(name = "offset") Integer offset, @WebParam(name = "limit") Integer limit) {
        return ProjectService.getInstance().findAllWithPagination(offset, limit).toArray(new ProjectDTO[0]);
    }

    @WebMethod
    public ProjectDTO getProjectById(@WebParam(name = "id") Long id) {
        return ProjectService.getInstance().findById(id);
    }

    @WebMethod
    public String createProject(@WebParam(name = "projectDTO") ProjectDTO projectDTO) {
        if(ProjectService.getInstance().save(projectDTO)) {
            return "Project created!";
        } else {
            return "Project already exists!";
        }
    }

    @WebMethod
    public String updateProject(@WebParam(name = "id") Long id, @WebParam(name = "projectDTO") ProjectDTO projectDTO) {
        if(ProjectService.getInstance().update(projectDTO)) {
            return "Project updated!";
        } else {
            return "Project does not exist!";
        }
    }

    @WebMethod
    public String deleteProject(@WebParam(name = "id") Long id) {
        if(ProjectService.getInstance().deleteProject(id)) {
            return "Project deleted!";
        } else {
            return "Project does not exist!";
        }
    }

    @WebMethod
    public ProjectDTO[] getEmployeesByProjectId(@WebParam(name = "id") Long id) {
        return ProjectService.getInstance().findEmployeesByProjectId(id).toArray(new ProjectDTO[0]);
    }
}
