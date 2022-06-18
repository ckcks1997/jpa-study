package com.example.jpql7;

import com.example.proxyrelation5.Member5;
import com.example.type6.Member6;

import javax.persistence.*;
import java.util.List;

public class JpaMain7 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2db");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();


        Member7 m = new Member7();
        m.setUsername("mem1");
        m.setAge(10);
        Member7 m2 = new Member7();
        m2.setUsername("mem2");
        m2.setAge(20);
        em.persist(m);
        em.persist(m2);
        //TypedQuery는 반환타입이 명확할 때, Qurty는 명확치 않을때
       TypedQuery<Member7> q1= em.createQuery("select m from Member7 m", Member7.class);
       TypedQuery<Member7> q2= em.createQuery("select m from Member7 m where m.id=1", Member7.class);
       Query q3= em.createQuery("select m.username, m.age from Member7 m");


        List<Member7> resultList = q1.getResultList();
        Member7 singleResult = q2.getSingleResult();

        List resultList1 = q3.getResultList();

        //파라미터 바인딩
       TypedQuery<Member7> q4= em.createQuery("select m from Member7 m where m.username= :username", Member7.class);
        q4.setParameter("username", "mem1");

        //메소드 체인방식도 가능
        /*
        Member7 res= em.createQuery("select m  from Member7 m where m.username= :username", Member7.class)
        .setParameter("username", "mem1")
        .getSingleResult();
        * */
        Member7 res = q4.getSingleResult();
        System.out.println(res);

        tx.commit();
        em.close();
        emf.close();
    }

    private static void printM(Member5 member){
        System.out.println("member = "+member.getUsername());
    }
}
