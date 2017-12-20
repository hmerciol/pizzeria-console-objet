package fr.pizzeria.dao;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

/**
 * @author hmerciol
 *
 */
public interface IPizzaDao {
	
	/**
	 * Renvoie la liste des pizzas actuellement au menu
	 * @return
	 */
	public Pizza[] findAllPizzas();
	
	/**
	 * Ajoute une nouvelle pizza au menu
	 * @param pizza
	 * @throws SavePizzaException
	 */
	public void saveNewPizza(Pizza pizza) throws SavePizzaException;
	
	/**
	 * Modifie une pizza du menu identifiée par son code
	 * @param codePizza
	 * @param pizza
	 * @throws UpdatePizzaException
	 */
	public void updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException;
	
	/**
	 * Supprime une pizza du menu identifiée par son code
	 * @param codePizza
	 * @throws DeletePizzaException
	 */
	public void deletePizza(String codePizza) throws DeletePizzaException;

}
