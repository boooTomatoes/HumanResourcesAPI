package service;

import enums.VacationStatus;
import mappers.VacationMapper;
import persistence.dto.VacationDTO;
import persistence.entities.Employee;
import persistence.entities.Vacation;
import persistence.repository.EmployeeRepository;
import persistence.repository.VacationRepository;
import persistence.util.TransactionUtil;

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

    public List<VacationDTO> findVacationsByEmployeeId(Long id) {
        return (List<VacationDTO>) TransactionUtil.doInTransaction(entityManager -> {
            return VacationMapper.INSTANCE.collectionToDto(VacationRepository.getInstance().findByEmployeeId(id, entityManager));
        });
    }
}
