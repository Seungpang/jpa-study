package hellojpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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

            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);

            Member member = new Member();
            member.setUsername("hello");
            member.setTeam(team);
            em.persist(member);

            Member member2 = new Member();
            member2.setUsername("hello");
            member2.setTeam(teamB);
            em.persist(member2);

            em.flush();
            em.clear();

            //Member findMember = em.find(Member.class, member.getId());
            //Member m = em.find(Member.class, member.getId());

            List<Member> members = em.createQuery("select m from Member m join fetch m.team", Member.class)
                .getResultList();

            tx.commit(); // 커밋시점에 DB에 저장된다.
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

}
