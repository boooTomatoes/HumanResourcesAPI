package service;

import mappers.AttendanceMapper;
import persistence.dto.AttendanceDTO;
import persistence.entities.AttendanceSheet;
import persistence.repository.AttendanceRepository;
import persistence.repository.EmployeeRepository;
import persistence.util.TransactionUtil;

import java.util.List;


public class AttendanceService extends BaseService<AttendanceSheet, AttendanceDTO,Long>{
    private AttendanceService() {
        super(AttendanceRepository.getInstance(), AttendanceMapper.INSTANCE);
    }
    private static final AttendanceService INSTANCE = new AttendanceService();
    public static AttendanceService getInstance() {
        return INSTANCE;
    }

    public boolean checkAuthentication(String username, String password, Long id) {
        return TransactionUtil.doInTransaction(entityManager -> {
            return AttendanceRepository.getInstance().checkAuthentication(username, password, entityManager, id);
        });
    }

    public boolean registerAttendance(Long id) {
        return TransactionUtil.doInTransaction(entityManager -> {
            if(!AttendanceRepository.getInstance().validateCheckIn(id, entityManager)){
                return false;
            }
            AttendanceSheet attendanceSheet = new AttendanceSheet();
            attendanceSheet.setEmployee(EmployeeRepository.getInstance().findById(id, entityManager));
            return AttendanceRepository.getInstance().save(attendanceSheet, entityManager);
        });
    }


    public AttendanceDTO getAttendance(Long id, String date) {
        return TransactionUtil.doInTransaction(entityManager -> {
            return AttendanceMapper.INSTANCE.toDTO(AttendanceRepository.getInstance().findByEmployeeIdAndDate(id, date, entityManager));
        });
    }

    public List<AttendanceDTO> getAttendance(Long id, int offset, int limit) {
        return (List<AttendanceDTO>) TransactionUtil.doInTransaction(entityManager -> {
            return AttendanceMapper.INSTANCE.collectionToDto(AttendanceRepository.getInstance().findByEmployeeId(id, entityManager, offset, limit));
        });
    }


}
