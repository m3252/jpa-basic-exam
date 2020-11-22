package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {


              // JPQL 사용법
//            List<Member> resultList = em.createQuery(
//                    "select m from Member m where m.username like '%moon%'", Member.class).getResultList();
//

              // Criteria 사용법
//            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
//            CriteriaQuery<Member> query = criteriaBuilder.createQuery(Member.class);
//
//            Root<Member> m = query.from(Member.class);
//
//            CriteriaQuery<Member> criteriaQuery = query.select(m).where(criteriaBuilder.equal(m.get("username"), "kim"));
//            List<Member> resultList = em.createQuery(criteriaQuery).getResultList();


            Member member = new Member();
            member.setUsername("member1");
            em.persist(member);

            //flush 는 commit, query 시점에 자동처리
            //dbconn.executeQuery("select * from member"); 결과 0
            em.flush();


//            for(Member mem : resultList){
//                System.out.println("member = "  +mem);
//            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }

}
