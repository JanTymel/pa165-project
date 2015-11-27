/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tireservice.dao;

import cz.muni.fi.pa165.tireservice.entity.Order;
import cz.muni.fi.pa165.tireservice.entity.User;
import cz.muni.fi.pa165.tireservice.enums.CarType;
import cz.muni.fi.pa165.tireservice.enums.OrderState;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Samuel Baniar
 */
@Repository
@Transactional
public class OrderDaoImpl implements OrderDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Order o) {
        em.persist(o);
    }

    @Override
    public Order findById(Long id) {
        return em.find(Order.class, id);
    }

    @Override
    public List<Order> findAll() {
        return em.createQuery("Select o from Order o", Order.class).getResultList();
    }

    @Override
    public void remove(Order o) throws IllegalArgumentException {
        em.remove(findById(o.getId()));
    }

    @Override
    public List<Order> findByUser(User u) {
        TypedQuery<Order> query = em.createQuery(
				"Select o from Order o where o.customer = :userid",
				Order.class);

		query.setParameter("userid", u);
		return query.getResultList();
    }

    @Override
    public List<Order> findByState(OrderState state) {
        TypedQuery<Order> query = em.createQuery(
				"SELECT o FROM Order o WHERE o.state = :state", Order.class);
		query.setParameter("state", state);
		return query.getResultList();
    }

    @Override
    public List<Order> findByCarType(CarType ct) {
        TypedQuery<Order> query = em.createQuery(
				"SELECT o FROM Order o WHERE o.carType = :state", Order.class);
		query.setParameter("state", ct);
		return query.getResultList();
    }

    @Override
    public List<Order> getOrdersCreatedBetween(Date start, Date end) {
        TypedQuery<Order> query = em
				.createQuery(
						"SELECT o FROM Order o WHERE o.created BETWEEN :startDate AND :endDate ",
						Order.class);
		query.setParameter("startDate", start);
		query.setParameter("endDate", end);
		return query.getResultList();
    }

    @Override
    public Order update(Order order){
        return em.merge(order);
    }
}
