package com.example.proxyrelation5;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain2 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2db");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Member55 m = new Member55();
        m.setUsername("member1");
        m.setHomeAddress(new Address("a","a", "a"));
        m.getFavoriteFoods().add("치킨");
        m.getFavoriteFoods().add("족발");
        m.getFavoriteFoods().add("피자");

        m.getAddressList().add(new Address("1","1","1"));
        m.getAddressList().add(new Address("2","2","2"));

        em.persist(m);

        em.flush();
        em.clear();
        System.out.println("==========");
        //값 타입들은 지연로딩이다.
        Member55 m6 = em.find(Member55.class, m.getId());

        System.out.println("----");
        List<Address> addressHistory = m6.getAddressList();

        //이때 쿼리가 날아감
        for(Address a : addressHistory){
            System.out.println(a);
        }

        //값 타입은 수정이 안된다.
        //m6.getHomeAddress().setCity("new ct");

        //다음과 같이 새로 넣어야 한다.
        m6.setHomeAddress(new Address("new city", "1","1"));

        //String도 값 타입이기 때문에 수정이 아닌 삭제/추가
        m6.getFavoriteFoods().remove("치킨");
        m6.getFavoriteFoods().add("한식");

        System.out.println("---");
        m6.getAddressList().remove(new Address("1", "1","1"));
        m6.getAddressList().add(new Address("new city123", "1","1"));

        tx.commit();
        em.close();
        emf.close();
    }

    private static void printM(Member5 member){
        System.out.println("member = "+member.getUsername());
    }
}
