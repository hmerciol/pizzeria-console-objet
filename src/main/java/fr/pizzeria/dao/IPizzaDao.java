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
	 * Pour r�cup�rer le type de DAO configur� dans le properties
	 * 
	 * @return L'impl�mentation de la DAO corespondante
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
	 *             Envoy� si la BDD est d�connect�e
	 */
	public List<Pizza> findAllPizzas() throws StockageException;

	/**
	 * Ajoute une nouvelle pizza au menu
	 * 
	 * @param pizza
	 *            La pizza � ajouter
	 * @throws StockageException
	 *             Envoy� si le code de la pizza est d�j� utilis� par une autre
	 *             pizza du menu ou si la BDD est d�connect�e
	 */
	public void saveNewPizza(Pizza pizza) throws StockageException;

	/**
	 * Modifie une pizza du menu identifi�e par son code
	 * 
	 * @param codePizza
	 *            Le code de la pizza � modifier
	 * @param pizza
	 *            La nouvelle pizza � ins�rer � la place de celle s�lectionn�e
	 * @throws StockageException
	 *             Envoy� si la pizza � modifier n'existe pas ou si une pizza autre
	 *             que celle � modifier utilise d�j� ce code ou si la BDD est
	 *             d�connect�e
	 */
	public void updatePizza(String codePizza, Pizza pizza) throws StockageException;

	/**
	 * Supprime une pizza du menu identifi�e par son code
	 * 
	 * @param codePizza
	 *            Le code de la pizza � supprimer
	 * @throws StockageException
	 *             Envoy� si la pizza n'existe pas ou si la BDD est d�connect�e
	 */
	public void deletePizza(String codePizza) throws StockageException;

}
