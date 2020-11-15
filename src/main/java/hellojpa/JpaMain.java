package hellojpa;

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

            Address address = new Address("city", "Street", "zipcode");

            Member member = new Member();
            member.setUsername("hello");
            member.setHomeAddress(address);
            member.setWorkPeriod(new Period());

            Address newAddress = new Address("newCity", address.getStreet(), address.getZipcode());
            member.setHomeAddress(newAddress);

            em.persist(member);

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
