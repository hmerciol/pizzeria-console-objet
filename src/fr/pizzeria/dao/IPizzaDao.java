package fr.pizzeria.dao;

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
	 * @return
	 */
	public boolean saveNewPizza(Pizza pizza);
	
	/**
	 * Modifie une pizza du menu identifiée par son code
	 * @param codePizza
	 * @param pizza
	 * @return
	 */
	public boolean updatePizza(String codePizza, Pizza pizza);
	
	/**
	 * Supprime une pizza du menu
	 * @param codePizza
	 * @return
	 */
	public boolean deletePizza(String codePizza);

}
