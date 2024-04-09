package service;

import mappers.AttendanceMapper;
import persistence.dto.AttendanceDTO;
import persistence.entities.AttendanceSheet;
import persistence.repository.AttendanceRepository;


public class AttendanceService extends BaseService<AttendanceSheet, AttendanceDTO,Long>{
    private AttendanceService() {
        super(new AttendanceRepository(), AttendanceMapper.INSTANCE);
    }
    private static final AttendanceService INSTANCE = new AttendanceService();
    public static AttendanceService getInstance() {
        return INSTANCE;
    }

}
