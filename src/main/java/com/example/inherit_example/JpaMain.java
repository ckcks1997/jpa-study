package com.example.inherit_example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2db");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Book4 b = new Book4();
        b.setName("JPA");
        b.setAuthor("kk");

        em.persist(b);

        tx.commit();
        em.close();
        emf.close();


    }
}
