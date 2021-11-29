import com.musma.hun.domain.Member;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        Member member = createMember("name", "address");

        mergeMember(member);
    }

    private static void mergeMember(Member member) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        Member mergeMember = em.merge(member);
        transaction.commit();

        System.out.println("member = " + member);
        System.out.println("mergeMember = " + mergeMember);

        mergeMember.setName("update");
        transaction.begin();
        transaction.commit();
        Member find = em.find(Member.class, mergeMember.getId());
        System.out.println("find = " + find);
        em.close();

    }

    static Member createMember(String name, String address) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        Member member = new Member();
        member.setName(name);
        member.setAddress(address);

        transaction.commit();
        em.close();

        return member;
    }
}