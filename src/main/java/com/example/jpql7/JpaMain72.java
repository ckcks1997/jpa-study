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




        for (int i = 0; i < 10; i++) {
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

        Team7 ft = em.createQuery("select t from Team7 t where t.name='team1'",Team7.class).getSingleResult();
        Member7 m = new Member7();
        m.setUsername("mem11");
        m.setAge(11);
        m.setTeam7(ft);
        m.setMemberType7(MemberType7.ADMIN);
        em.persist(m);

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

        query = "select m from Member7 m  "; //이렇게하면 m.team값을 조회할 때 마다 쿼리를 날림(N+1)
        query = "select m from Member7 m left join fetch m.team7";//join해서 한번에 값 가져옴
        query = "select count(m) from Member7 m left join m.team7 group by m.team7";//
        query = "select distinct t from Team7 t join fetch t.members7";//

        //주의: 패치조인은 별칭 되도록 x
        query = "select  t from Team7 t join fetch t.members7 m";//



        //List<Member7> result = em.createQuery(query, Member7.class).getResultList();
        List<Object[]> result = em.createQuery(query).getResultList();
/*        for (Member7 member7 : result) {
            System.out.println(member7.getUsername()+" "+member7.getTeam7().getName());
        } */
        for (Object member7 : result) {
            System.out.println(member7);
           // System.out.println( (Team7)member7+" "+((Team7) member7).getMembers7());
        }


        //네임드 쿼리
        em.createNamedQuery("Member7.findByUsername", Member7.class)
                .setParameter("username", "회원1")
                .getResultList();

        //벌크연산
        int resultbulk = em.createQuery("update Member7 m set m.age = 20")
                        .executeUpdate();

        System.out.println("resultbulk="+resultbulk);

        tx.commit();
        em.close();
        emf.close();
    }

    private static void printM(Member5 member){
        System.out.println("member = "+member.getUsername());
    }
}
