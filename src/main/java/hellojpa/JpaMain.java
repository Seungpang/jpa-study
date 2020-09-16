package hellojpa;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member1 = new Member();
            member1.setUsername("A");

            Member member2 = new Member();
            member2.setUsername("A");

            Member member3 = new Member();
            member3.setUsername("A");

            System.out.println("===========");

            em.persist(member1);
            em.persist(member2);
            em.persist(member3);

            System.out.println("member1 = " + member1);
            System.out.println("member2 = " + member2);
            System.out.println("member3 = " + member3);

            System.out.println("===========");

            tx.commit(); // 커밋시점에 DB에 저장된다.
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
