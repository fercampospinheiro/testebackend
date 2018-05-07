package br.com.uol.testebackend.infra.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Fornece um EntityManger para o teste unitários
 */
public class JpaUtilTest {
    protected static EntityManagerFactory emf;

    protected EntityManager em;

    public static void createEntityManagerFactory() {
        emf = Persistence.createEntityManagerFactory("PetstorePu");
    }

    public static void closeEntityManagerFactory() {
        emf.close();
    }

    public  void beginTransaction() {
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    public void rollbackTransaction() {   
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }

        if (em.isOpen()) {
            em.close();
        }
    } 

    public EntityManager getEntityManager() {
        return em;
    }
    
}
