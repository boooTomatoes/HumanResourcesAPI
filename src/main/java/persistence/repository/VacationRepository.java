package persistence.repository;

import jakarta.persistence.EntityManager;
import persistence.entities.Vacation;

import java.util.Collection;

public class VacationRepository extends GenericRepository<Vacation, Long>{
    private VacationRepository() {
        super(Vacation.class);
    }
    private static final VacationRepository INSTANCE=new VacationRepository();
    public static VacationRepository getInstance(){
        return INSTANCE;
    }

    public Collection<Vacation> findByEmployeeId(Long id, EntityManager entityManager,Integer offset,Integer limit) {
        return entityManager.createQuery("SELECT v FROM Vacation v WHERE v.employee.id = :id", Vacation.class)
                .setParameter("id", id)
                .getResultList();
    }
}
