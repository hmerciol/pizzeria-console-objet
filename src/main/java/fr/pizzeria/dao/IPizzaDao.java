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
	 *            La pizza à ajouter
	 * @throws SavePizzaException
	 *             Envoyé si le code de la pizza est déjà utilisé par une autre
	 *             pizza du menu
	 */
	public void saveNewPizza(Pizza pizza) throws SavePizzaException;

	/**
	 * Modifie une pizza du menu identifiée par son code
	 * 
	 * @param codePizza
	 *            Le code de la pizza à modifier
	 * @param pizza
	 *            La nouvelle pizza à insérer à la place de celle sélectionnée
	 * @throws UpdatePizzaException
	 *             Envoyé si la pizza à modifier n'existe pas ou si une pizza autre
	 *             que celle à modifier utilise déjà ce code
	 */
	public void updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException;

	/**
	 * Supprime une pizza du menu identifiée par son code
	 * 
	 * @param codePizza
	 *            Le code de la pizza à supprimer
	 * @throws DeletePizzaException
	 *             Envoyé si la pizza n'existe pas
	 */
	public void deletePizza(String codePizza) throws DeletePizzaException;

}
