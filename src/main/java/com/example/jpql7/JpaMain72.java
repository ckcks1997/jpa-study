package com.example.jpql7;

import com.example.proxyrelation5.Member5;

import javax.persistence.*;
import java.util.List;

public class JpaMain72 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2db");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();




        for (int i = 0; i < 100; i++) {
            Team7 team = new Team7();
            team.setName("team"+i);
            em.persist(team);
            Member7 m = new Member7();
            m.setUsername("mem"+i);
            m.setAge(i);
            m.setTeam7(team);
            m.setMemberType7(MemberType7.ADMIN);
            em.persist(m);

        }



        em.flush();
        em.clear();


        //페이징

        List<Member7> result1 = em.createQuery(
                "select m from Member7 m order by m.age desc", Member7.class)
                .setFirstResult(1)
                .setMaxResults(10)
                .getResultList();

        for (Member7 member7 : result1) {
            System.out.println(member7);
        }

        //조인
        em.flush();
        em.clear();
        // inner join, inner 생략가능
            //String query = "select m from Member7 m inner join m.team7 t";
        //left join
            //String query = "select m from Member7 m left join m.team7 t";
        //세타조인
            //String query = "select m from Member7 m, Team7 t where m.username = t.name";
        //조인 필터링
            //String query = "select m from Member7 m left join Team7 t on m.username = t.name";
       // String query = "select m,t from Member7 m left join m.team7 t on t.name='team1'" ;
        //List<Member7> result2 = em.createQuery(query, Member7.class).getResultList();

        //기타표현식
        //String query = "select m,t, 'hello', true from Member7 m left join m.team7 t on t.name='team1'" ;
        //enum표현
       // String query = "select m from Member7 m " +
       //         "where m.memberType7 = com.example.jpql7.MemberType7.ADMIN" ;

        //조건식
        String query = "select " +
                "case when m.age <= 10 then '학생요금'" +
                "     when m.age > 60 then '경로요금'" +
                " else '일반' end" +
                " from Member7 m " ;

        query = "select function('group_concat', i.username) from Member7 i";

        List<Object[]> result2 = em.createQuery(query).getResultList();
        System.out.println(result2.size());
/*        for (Object[] objects : result2) {
            for (Object object : objects) {
                System.out.print(object+" ");
            }
            System.out.println();
        }*/

        for (Object objects : result2) {

            System.out.print(objects+" ");
            System.out.println();
        }




        tx.commit();
        em.close();
        emf.close();
    }

    private static void printM(Member5 member){
        System.out.println("member = "+member.getUsername());
    }
}
