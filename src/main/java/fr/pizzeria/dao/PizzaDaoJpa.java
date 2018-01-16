package fr.pizzeria.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.utils.PizzaValidator;

/**
 * @author hmerciol
 *
 */
public class PizzaDaoJpa implements IPizzaDao {
	
	/**
	 * Pour gérer les connections à la BDD
	 */
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("pizzeria");
	
	public void closeConnection() {
		factory.close();
	}
	
	public void resetConnection() {
		factory.close();
		factory = Persistence.createEntityManagerFactory("pizzeria");
	}

	@Override
	public List<Pizza> findAllPizzas() throws StockageException {
		EntityManager manager = factory.createEntityManager();
		
		TypedQuery<Pizza> query = manager.createQuery("select p from Pizza p", Pizza.class);
		List<Pizza> result = query.getResultList();
		manager.close();
		
		return result;
	}

	@Override
	public void saveNewPizza(Pizza pizza) throws StockageException {
		if(!PizzaValidator.pizzaValide(pizza)) {
			throw new SavePizzaException("Pizza non valide");
		}
		
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		
		transaction.begin();
		manager.persist(pizza);
		transaction.commit();
		manager.close();
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) throws StockageException {
		if(!PizzaValidator.pizzaValide(pizza)) {
			throw new UpdatePizzaException("Pizza non valide");
		}
		
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		
		TypedQuery<Pizza> query = manager.createQuery("select p from Pizza p where p.code=:code", Pizza.class);
		query.setParameter("code", codePizza);
		Pizza oldPizza = query.getSingleResult();
		
		transaction.begin();
		oldPizza.setCode(pizza.getCode());
		oldPizza.setNom(pizza.getNom());
		oldPizza.setCategorie(pizza.getCategorie());
		oldPizza.setPrix(pizza.getPrix());
		transaction.commit();
		manager.close();
	}

	@Override
	public void deletePizza(String codePizza) throws StockageException {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		
		TypedQuery<Pizza> query = manager.createQuery("select p from Pizza p where p.code=:code", Pizza.class);
		query.setParameter("code", codePizza);
		Pizza pizza = query.getSingleResult();
		
		transaction.begin();
		manager.remove(pizza);
		transaction.commit();
		manager.close();
	}

}
