package fr.maxime.dao;

import fr.maxime.entity.Etudient;
import jakarta.persistence.EntityManager;

import java.util.List;

public class EtudientDAO {
    EntityManager entityManager;
    public EtudientDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public  void create(Etudient etudient) {
        entityManager.getTransaction().begin();
        entityManager.persist(etudient);
        entityManager.getTransaction().commit();
    }

    public  List<Etudient> read() {
        return entityManager.createQuery("SELECT  e from Etudient e", Etudient.class).getResultList();

    }

    public  void update(Etudient etudient) {
        entityManager.getTransaction().begin();
        entityManager.merge(etudient);
        entityManager.getTransaction().commit();
    }

    public  void delete(Etudient etudient) {
        entityManager.getTransaction().begin();
        entityManager.remove(etudient);
        entityManager.getTransaction().commit();
    }

    public  Etudient getById(int id) {
        return entityManager.find(Etudient.class, id);
    }
}
