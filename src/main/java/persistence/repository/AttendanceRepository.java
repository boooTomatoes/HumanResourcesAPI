package persistence.repository;

import jakarta.persistence.EntityManager;
import persistence.entities.AttendanceSheet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class AttendanceRepository extends GenericRepository<AttendanceSheet,Long> {
    private AttendanceRepository() {
        super(AttendanceSheet.class);
    }
    private static final AttendanceRepository INSTANCE = new AttendanceRepository();
    public static AttendanceRepository getInstance() {
        return INSTANCE;
    }


    public Boolean checkAuthentication(String username, String password, EntityManager entityManager,Long id) {
        return entityManager.createQuery("SELECT COUNT(a) FROM Employee a WHERE a.username = :username AND a.password = :password AND a.id = :id", Long.class)
                .setParameter("username", username)
                .setParameter("password", password)
                .setParameter("id", id)
                .getSingleResult() > 0;

    }

    public boolean validateCheckIn(Long employeeId, EntityManager entityManager){
        Date today = new Date();
        Long count = entityManager.createQuery("SELECT COUNT(a) FROM AttendanceSheet a WHERE a.employee.id = :employeeId AND a.date = :today", Long.class)
                .setParameter("employeeId", employeeId)
                .setParameter("today", today)
                .getSingleResult();

        return count <= 0;
    }

    public AttendanceSheet findByEmployeeIdAndDate(Long id, String date, EntityManager entityManager) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        System.out.println(localDate);
        return entityManager.createQuery("SELECT a FROM AttendanceSheet a WHERE a.employee.id = :id AND a.date = :date", AttendanceSheet.class)
                .setParameter("id", id)
                .setParameter("date", localDate)
                .getSingleResult();
    }
}
