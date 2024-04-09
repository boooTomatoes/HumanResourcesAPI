package persistence.repository;

import persistence.entities.AttendanceSheet;

public class AttendanceRepository extends GenericRepository<AttendanceSheet,Long> {
    public AttendanceRepository() {
        super(AttendanceSheet.class);
    }


}
