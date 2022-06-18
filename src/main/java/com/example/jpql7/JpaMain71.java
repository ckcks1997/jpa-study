package com.example.jpql7;

import com.example.proxyrelation5.Member5;

import javax.persistence.*;
import java.util.List;

public class JpaMain71 {

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

        em.flush();
        em.clear();

        Member7 res= em.createQuery("select m  from Member7 m where m.username= :username", Member7.class)
        .setParameter("username", "mem1")
        .getSingleResult();

        //참고로 jpql에서 조회한 값도 영속성 컨텍스트에서 관리가 된다.
        //다음은 업데이트 쿼리가 나간다.
        res.setAge(3);
        System.out.println(res);

        //조인은 다음과같이 명확하게 표현해야 한다.
        List<Team7> res2= em.createQuery("select t from Member7 m join m.team7 t", Team7.class)
                .getResultList();

        //임베디드 타입 프로젝션
        List<Address7> res3= em.createQuery("select o.address7 from Order7 o", Address7.class)
                .getResultList();

        //스칼라
        System.out.println("스칼라---");
        //1. Query
        List res4= em.createQuery("select distinct m.username, m.age from Member7 m").getResultList() ;
        Object o = res4.get(0);
        Object[] result = (Object[]) o;
        System.out.println(result[0]);
        System.out.println(result[1]);

        //2. Object[]타입선언
        List<Object[]> res5= em.createQuery("select distinct m.username, m.age from Member7 m")
                .getResultList() ;

        for (Object[] objects : res5) {
            System.out.println(objects[0]);
            System.out.println(objects[1]);
        }
    //3.new 명령어 조회(DTO사용)
        List<MemberDTO> res6= em.createQuery("select distinct" +
                        " new com.example.jpql7.MemberDTO(m.username, m.age)" +
                        " from Member7 m", MemberDTO.class)
                .getResultList() ;

        for (MemberDTO memberDTO : res6) {
            System.out.println(memberDTO);
        }

        tx.commit();
        em.close();
        emf.close();
    }

    private static void printM(Member5 member){
        System.out.println("member = "+member.getUsername());
    }
}
