package com.uls.dao;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.uls.generated.User;

public class JpaUserDao implements Dao<User> {

	private EntityManager em = Persistence.createEntityManagerFactory("Car_Occupation_Tool").createEntityManager();
	
	@Override
	public Optional<User> selectById(int id) {
		return Optional.ofNullable(em.find(User.class, id));
	}

	@Override
	public List<User> selectAll() {
		Query query = em.createNamedQuery("User.findAll");
		return query.getResultList();
	}

	@Override
	public boolean insert(User user) {
		executeInsideTransaction(em -> em.persist(user));
		return true;
	}

	@Override
	public boolean update(User user) {
		executeInsideTransaction(em -> em.merge(user));
		return true;
	}

	@Override
	public boolean delete(User user) {
		executeInsideTransaction(em -> em.remove(user));
		return true;
	}
	
	private void executeInsideTransaction(Consumer<EntityManager> action) {
	       EntityTransaction tx = em.getTransaction();
	        try {
	            tx.begin();
	            action.accept(em);
	            tx.commit(); 
	        }
	        catch (RuntimeException e) {
	            tx.rollback();
	            throw e;
	        }
	}

}
