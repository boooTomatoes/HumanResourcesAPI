package persistence.entities;


import jakarta.persistence.*;

import java.util.Date;

@Entity
public class AttendanceSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Temporal(TemporalType.TIMESTAMP)
    private Date checkInTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkOutTime;

}
