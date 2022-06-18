package com.example.proxyrelation5;

import com.example.hellojpa.RoleType;

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

        Team5 team = new Team5();
        team.setName("TeamA");
        Team5 team2 = new Team5();
        team.setName("TeamB");
        em.persist(team);
        em.persist(team2);

        Member5 member = new Member5();
        member.setUsername("hello");
        member.setTeam(team);
        Member5 member2 = new Member5();
        member2.setUsername("hello2");
        member2.setTeam(team2);
        em.persist(member2);
        em.flush();
        em.clear();

        //find는 바로 값을 조회해 온다.
       // Member5 member2 = em.find(Member5.class, 1L);

        //getReference는 가짜값(하이버네이트 프록시객체)을 member2에 넣어뒀다가
        //실제 조회가 되는 순간 값을 조회한다.
       // Member5 memberse = em.getReference(Member5.class, 1L);
       // System.out.println(member2.getClass());
        System.out.println("--------------");
        //printM(memberse);

        System.out.println("-------");
        List<Member5> members =
                em.createQuery("select m from Member5 m ",
                        Member5.class).getResultList();


        //영속성 전이

        Child c = new Child();
        Child c2 = new Child();

        Parent p = new Parent();
        p.addChild(c);
        p.addChild(c2);

        em.persist(p);
/*        em.persist(c);
        em.persist(c2);*/
        em.flush();
        em.clear();
        Parent fp = em.find(Parent.class, p.getId());
        fp.getChildList().remove(0);



        Integer a = 1;
        Integer b = a;
        a=2;
        System.out.println(a+" "+b);

        //part6.값타입

        Member5 m5 = new Member5();
        m5.setUsername("hellooo");
        m5.setHomeAddress(new Address("city", "street", "10"));
        m5.setWorkPeriod(new Period());

        em.persist(m5);
        em.flush();

        tx.commit();
        em.close();
        emf.close();
    }

    private static void printM(Member5 member){
        System.out.println("member = "+member.getUsername());
    }
}
