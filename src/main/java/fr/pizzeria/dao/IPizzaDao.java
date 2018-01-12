package fr.pizzeria.dao;

import java.util.List;
import java.util.ResourceBundle;

import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.Pizza;

/**
 * @author hmerciol
 *
 */
public interface IPizzaDao {

	/**
	 * Pour récupérer le type de DAO configuré dans le properties
	 * 
	 * @return L'implémentation de la DAO corespondante
	 */
	public static IPizzaDao getPizzaDao() {
		String daoType = ResourceBundle.getBundle("jdbc").getString("dao.type");
		IPizzaDao pizzaDao = null;
		switch (daoType) {
		case "list":
			pizzaDao = new PizzaDaoImpl();
			break;
		case "file":
			pizzaDao = new PizzaDaoFichier();
			break;
		case "jdbc":
			pizzaDao = new PizzaDaoDB();
			break;
		}
		return pizzaDao;
	}

	/**
	 * Renvoie la liste des pizzas actuellement au menu
	 * 
	 * @return La liste des pizzas
	 * @throws StockageException
	 *             Envoyé si la BDD est déconnectée
	 */
	public List<Pizza> findAllPizzas() throws StockageException;

	/**
	 * Ajoute une nouvelle pizza au menu
	 * 
	 * @param pizza
	 *            La pizza à ajouter
	 * @throws StockageException
	 *             Envoyé si le code de la pizza est déjà utilisé par une autre
	 *             pizza du menu ou si la BDD est déconnectée
	 */
	public void saveNewPizza(Pizza pizza) throws StockageException;

	/**
	 * Modifie une pizza du menu identifiée par son code
	 * 
	 * @param codePizza
	 *            Le code de la pizza à modifier
	 * @param pizza
	 *            La nouvelle pizza à insérer à la place de celle sélectionnée
	 * @throws StockageException
	 *             Envoyé si la pizza à modifier n'existe pas ou si une pizza autre
	 *             que celle à modifier utilise déjà ce code ou si la BDD est
	 *             déconnectée
	 */
	public void updatePizza(String codePizza, Pizza pizza) throws StockageException;

	/**
	 * Supprime une pizza du menu identifiée par son code
	 * 
	 * @param codePizza
	 *            Le code de la pizza à supprimer
	 * @throws StockageException
	 *             Envoyé si la pizza n'existe pas ou si la BDD est déconnectée
	 */
	public void deletePizza(String codePizza) throws StockageException;

}
