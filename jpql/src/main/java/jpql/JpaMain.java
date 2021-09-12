package jpql;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("관리자");
            member.setAge(10);
            member.setType(MemberType.ADMIN);

            member.setTeam(team);

            em.persist(member);

            em.flush();
            em.clear();

            String query =
                "select " +
                    "case when m.age <= 10 then '학생요금' " +
                    "     when m.age >= 60 then '경로요금' " +
                    "     else '일반요금' " +
                    "end " +
                    "from Member m";
            List<String> resultList = em.createQuery(query, String.class)
                .getResultList();

            String query2 = "select coalesce(m.username, '이름 없는 회원') from Member m ";
            List<String> resultList2 = em.createQuery(query2, String.class)
                .getResultList();

            String query3 = "select nullif(m.username, '관리자') as username " +
                "from Member m ";
            List<String> resultList3 = em.createQuery(query3, String.class)
                .getResultList();

            for (String s : resultList) {
                System.out.println("s = " + s);
            }

            for (String s : resultList2) {
                System.out.println("s = " + s);
            }

            for (String s : resultList3) {
                System.out.println("s = " + s);
            }

            tx.commit(); // 커밋시점에 DB에 저장된다.
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

}