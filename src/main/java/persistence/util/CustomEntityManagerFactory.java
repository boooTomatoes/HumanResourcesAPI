package persistence.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public enum CustomEntityManagerFactory {
    INSTANCE;

    private final EntityManagerFactory emf;

    CustomEntityManagerFactory() {
        emf = Persistence.createEntityManagerFactory("persistence-unit");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void close() {
        emf.close();
    }
}