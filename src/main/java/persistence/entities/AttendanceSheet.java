package persistence.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;
@Getter
@Entity
@Setter
public class AttendanceSheet extends BaseEntity{

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

    @PrePersist
    public void prePersist() {
        this.date = new Date();
        this.checkInTime = new Date();
    }

}
