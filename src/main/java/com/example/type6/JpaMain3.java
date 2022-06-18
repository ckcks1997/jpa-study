package com.example.type6;

import com.example.proxyrelation5.Member5;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain3 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2db");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
// JPQL 및 네이티브 쿼리
        em.createQuery(
                "select m from Member6 m  where m.username like '%kim%'",
                Member6.class
        ).getResultList();
        System.out.println("=====");
        em.createNativeQuery("select * from member", Member6.class).getResultList();


        Book6 b = new Book6();
        b.setName("책입니다");
        b.setAuthor("author");
        em.persist(b);
        em.flush();
        em.clear();

        List<Item6> result2 =
                em.createQuery("select i from Item6 i where type (i) = Book6", Item6.class)
                .getResultList();

            Item6 i = result2.get(0);
            if(i instanceof Book6) {
                System.out.println(i.getName());
            }


        tx.commit();
        em.close();
        emf.close();
    }

    private static void printM(Member5 member){
        System.out.println("member = "+member.getUsername());
    }
}
