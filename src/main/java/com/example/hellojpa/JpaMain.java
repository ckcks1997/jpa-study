package com.example.hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2db");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Team team = new Team();
        team.setName("TeamA");
        em.persist(team);

        Member member = new Member();
        member.setUsername("hello");
        member.setRoleType(RoleType.ADMIN);
        member.setTeam(team);
        em.persist(member);


        Member findMember = em.find(Member.class, member.getId());
        Team team22 = findMember.getTeam();

        System.out.println("=========");
        List<Member> members = findMember.getTeam().getMembers();
        for (Member member1 : members){
            System.out.println("m="+ member1.getUsername());
        }

        //연관관계의 주인은?
        Member member2 = new Member();
        member2.setUsername("member2");
        em.persist(member2);

        //mappedBy는 읽기전용이기 때문에 쿼리가 날아가도 실제 디비에 저장은 안됨!
        team.getMembers().add(member2); //member테이블을 보면 team_id값은 여전히 null이다.


        em.flush();
        em.clear();
//////////////////////////
        findMember = em.find(Member.class, member2.getId());
        findMember.setTeam(team);


        tx.commit(); // em.flush 자동호출
        em.close();
        emf.close();

      
    }
}
