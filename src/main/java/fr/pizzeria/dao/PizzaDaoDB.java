package fr.pizzeria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.utils.PizzaValidator;

/**
 * @author hmerciol
 *
 */
public class PizzaDaoDB implements IPizzaDao {

	/**
	 * Connection � la base de donn�es
	 */
	private Connection databaseConnection;

	/**
	 * Nom de la structure dans la base de donn�es
	 */
	private String dataSchema;

	/**
	 * Ouvrir la connection � la base de donn�es
	 * 
	 * @throws StockageException
	 *             En cas de probl�me lors de l'acc�s � la BDD
	 */
	public void connectionDB() throws StockageException {
		ResourceBundle jdbcProperties = ResourceBundle.getBundle("jdbc");
		try {
			Class.forName(jdbcProperties.getString("jdbc.driver"));
			databaseConnection = DriverManager.getConnection(jdbcProperties.getString("jdbc.url"),
					jdbcProperties.getString("jdbc.user"), jdbcProperties.getString("jdbc.pw"));
			dataSchema = jdbcProperties.getString("jdbc.data.schema");
		} catch (ClassNotFoundException e) {
			throw new StockageException("Probl�me lors de la connection � la base de donn�es");
		} catch (SQLException e) {
			throw new StockageException("Probl�me lors de la connection � la base de donn�es\n" + e.getMessage());
		}
	}

	/**
	 * Fermer la connection � la base de donn�es
	 * 
	 * @throws StockageException
	 *             En cas de probl�me lors de l'acc�s � la BDD
	 */
	public void closeConnection() throws StockageException {
		try {
			databaseConnection.close();
			databaseConnection = null;
		} catch (SQLException e) {
			throw new StockageException("Probl�me lors de la d�connection � la base de donn�es");
		}
	}

	/**
	 * Pour savoir si la base de donn�es est connect�e
	 * 
	 * @return true si connect�e
	 */
	public boolean isConnected() {
		return databaseConnection != null;
	}

	/**
	 * Pour bloquer une m�thode si la base n'est pas connect�e
	 * 
	 * @throws StockageException
	 *             Si non connect�e
	 */
	private void connectionTest() throws StockageException {
		if (databaseConnection == null) {
			throw new StockageException("La base de donn�es n'est pas connect�e");
		}
	}

	/**
	 * R�cup�re le tableau des pizzas stock� en BDD
	 * 
	 * @throws StockageException
	 *             En cas de probl�me lors de l'acc�s � la BDD
	 */
	private List<Pizza> getFromDB() throws StockageException {
		connectionTest();

		List<Pizza> menuPizzas = new ArrayList<>();

		try (Statement statement = databaseConnection.createStatement();
				ResultSet results = statement.executeQuery("SELECT * FROM " + dataSchema + ".pizza;")) {
			while (results.next()) {
				menuPizzas.add(new Pizza(results.getString("pizza_code"), results.getString("pizza_nom"),
						results.getFloat("pizza_prix"), CategoriePizza.valueOf(results.getString("pizza_categorie"))));
			}
		} catch (SQLException e) {
			throw new StockageException("Probl�me lors de la connection � la base de donn�es");
		}

		return menuPizzas;
	}

	@Override
	public List<Pizza> findAllPizzas() throws StockageException {
		return getFromDB();
	}

	@Override
	public void saveNewPizza(Pizza pizza) throws StockageException {
		connectionTest();
		
		if (!PizzaValidator.pizzaValide(pizza)) {
			throw new SavePizzaException("Pizza non valide");
		}

		String query = "INSERT INTO " + dataSchema
				+ ".pizza (pizza_code, pizza_nom, pizza_categorie, pizza_prix) VALUES (?, ?, ?, ?);";
		try (PreparedStatement statement = databaseConnection.prepareStatement(query)) {
			statement.setString(1, pizza.getCode());
			statement.setString(2, pizza.getNom());
			statement.setString(3, pizza.getCategorie().toString());
			statement.setDouble(4, pizza.getPrix());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new StockageException("Probl�me lors de l'ajout d'une pizza � la base de donn�es");
		}
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) throws StockageException {
		connectionTest();
		
		if (!PizzaValidator.pizzaValide(pizza)) {
			throw new UpdatePizzaException("Pizza non valide");
		}

		String query = "UPDATE " + dataSchema
				+ ".pizza SET pizza_code=?, pizza_nom=?, pizza_categorie=?, pizza_prix=? WHERE pizza_code=?;";
		try (PreparedStatement statement = databaseConnection.prepareStatement(query)) {
			statement.setString(1, pizza.getCode());
			statement.setString(2, pizza.getNom());
			statement.setString(3, pizza.getCategorie().toString());
			statement.setDouble(4, pizza.getPrix());
			statement.setString(5, codePizza);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new StockageException("Probl�me lors de la mise � jour d'une pizza dans la base de donn�es");
		}
	}

	@Override
	public void deletePizza(String codePizza) throws StockageException {
		connectionTest();

		String query = "DELETE FROM " + dataSchema + ".pizza WHERE pizza_code = ?;";
		try (PreparedStatement statement = databaseConnection.prepareStatement(query)) {
			statement.setString(1, codePizza);
			statement.executeQuery();
		} catch (SQLException e) {
			throw new StockageException("Probl�me lors de la suppression d'une pizza � la base de donn�es");
		}
	}

}
