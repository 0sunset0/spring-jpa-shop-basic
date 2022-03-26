package jpabook.jpashop;

import jpabook.jpashop.domain.Item;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

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
            Member member = new Member();
            member.setName("hello");

            Item item = new Item();
            item.setName("book");

            OrderItem orderItem = new OrderItem();
            orderItem.setItem(item);

            Order order = new Order();
            order.setMember(member);
            order.addOrderItem(orderItem);

            em.persist(member);
            em.persist(item);
            em.persist(orderItem);
            em.persist(order);

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
