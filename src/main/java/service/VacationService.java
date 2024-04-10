package service;

import enums.JobTitle;
import enums.VacationStatus;
import mappers.VacationMapper;
import persistence.dto.VacationDTO;
import persistence.entities.Employee;
import persistence.entities.Vacation;
import persistence.repository.EmployeeRepository;
import persistence.repository.VacationRepository;
import persistence.util.TransactionUtil;
import utils.Authorization;

import java.util.Base64;
import java.util.List;

public class VacationService extends BaseService<Vacation, VacationDTO, Long> {
    private VacationService() {
        super(VacationRepository.getInstance(), VacationMapper.INSTANCE);
    }

    private static final VacationService INSTANCE = new VacationService();

    public static VacationService getInstance() {
        return INSTANCE;
    }


    public boolean requestVacation(VacationDTO requestVacation) {

        return TransactionUtil.doInTransaction(entityManager -> {
            Employee employee = EmployeeRepository.getInstance().findById(requestVacation.getEmployeeId(), entityManager);
            System.out.println(employee.getVacations());
            boolean hasPendingVacations = employee.getVacations().stream()
                    .anyMatch(vacation -> vacation.getStatus().equals(VacationStatus.PENDING));

            if (hasPendingVacations) {
                return false;
            } else {
                Vacation vacation = VacationMapper.INSTANCE.toEntity(requestVacation);
                vacation.setEmployee(employee);
                return VacationRepository.getInstance().save(vacation, entityManager);

            }
        });
    }

    public List<VacationDTO> findVacationsByEmployeeId(Long id, Integer offset, Integer limit) {
        return (List<VacationDTO>) TransactionUtil.doInTransaction(entityManager -> {
            return VacationMapper.INSTANCE.collectionToDto(VacationRepository.getInstance().findByEmployeeId(id, entityManager,offset,limit));
        });
    }


    public boolean checkAuthorization(String headerString) {
        String[] values =Authorization.decode(headerString);
        String username = values[0];
        String password = values[1];
        return TransactionUtil.doInTransaction(entityManager -> {
            Employee emp = EmployeeRepository.getInstance().getEmployee(username, password, entityManager);
            if (emp == null) {
                return false;
            } else
                return (emp.getJob().getTitle().equals(JobTitle.MANAGER) || emp.getJob().getTitle().equals(JobTitle.HUMAN_RESOURCES));
        });
    }

    public boolean processVacation(String status, Long id) {
        return TransactionUtil.doInTransaction(entityManager -> {
            Vacation vacation = VacationRepository.getInstance().findById(id, entityManager);
            vacation.setStatus(VacationStatus.valueOf(status));
            return VacationRepository.getInstance().update(vacation, entityManager);
        });
    }
}
