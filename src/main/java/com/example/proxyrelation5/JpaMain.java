package com.example.proxyrelation5;

import com.example.hellojpa.Member;
import com.example.hellojpa.RoleType;
import com.example.hellojpa.Team;

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

        Team5 team = new Team5();
        team.setName("TeamA");
        em.persist(team);

        Member5 member = new Member5();
        member.setUsername("hello");
        member.setRoleType(RoleType.ADMIN);
        member.setTeam(team);
        em.persist(member);
        em.flush();
        em.clear();

        //find는 바로 값을 조회해 온다.
       // Member5 member2 = em.find(Member5.class, 1L);

        //getReference는 가짜값(하이버네이트 프록시객체)을 member2에 넣어뒀다가
        //실제 조회가 되는 순간 값을 조회한다.
        Member5 member2 = em.getReference(Member5.class, 1L);
        System.out.println(member2.getClass());
        System.out.println("--------------");
        printM(member2);

        tx.commit();
        em.close();
        emf.close();


    }

    private static void printM(Member5 member){
        System.out.println("member = "+member.getUsername());
    }
}
