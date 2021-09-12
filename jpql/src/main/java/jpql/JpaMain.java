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


            Member member2 = new Member();
            member2.setUsername("관리자");
            em.persist(member2);

            member.setTeam(team);

            em.persist(member);

            em.flush();
            em.clear();

            String query = "select 'a' || 'b' From Member m ";
            String query2 = "select substring(m.username,2,3) From Member m ";
            String query3 = "select locate('de','abcdefg') From Member m ";
            String query4 = "select function('group_concat', m.username) From Member m";

            List<String> resultList = em.createQuery(query4, String.class)
                .getResultList();

            for (String s : resultList) {
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