package fr.pizzeria.dao;

import java.util.List;

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
	 * 
	 * @return La liste des pizzas
	 */
	public List<Pizza> findAllPizzas();

	/**
	 * Ajoute une nouvelle pizza au menu
	 * 
	 * @param pizza
	 *            La pizza � ajouter
	 * @throws SavePizzaException
	 *             Envoy� si le code de la pizza est d�j� utilis� par une autre
	 *             pizza du menu
	 */
	public void saveNewPizza(Pizza pizza) throws SavePizzaException;

	/**
	 * Modifie une pizza du menu identifi�e par son code
	 * 
	 * @param codePizza
	 *            Le code de la pizza � modifier
	 * @param pizza
	 *            La nouvelle pizza � ins�rer � la place de celle s�lectionn�e
	 * @throws UpdatePizzaException
	 *             Envoy� si la pizza � modifier n'existe pas ou si une pizza autre
	 *             que celle � modifier utilise d�j� ce code
	 */
	public void updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException;

	/**
	 * Supprime une pizza du menu identifi�e par son code
	 * 
	 * @param codePizza
	 *            Le code de la pizza � supprimer
	 * @throws DeletePizzaException
	 *             Envoy� si la pizza n'existe pas
	 */
	public void deletePizza(String codePizza) throws DeletePizzaException;

}
