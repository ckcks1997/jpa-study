package com.example.inherit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2db");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Movie movie = new Movie();
        movie.setDirector("aa");
        movie.setActor("bbb");
        movie.setName("baram");
        movie.setPrice(10000);
        movie.setCreatedBy("someone");
        movie.setCreatedDate(LocalDateTime.now());

        em.persist(movie);
        tx.commit();
        em.close();
        emf.close();


    }
}
