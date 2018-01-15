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
	 * Connection à la base de données
	 */
	private Connection databaseConnection;
	
	private String dataSchema;

	/**
	 * Ouvrir la connection à la base de données
	 * 
	 * @throws StockageException
	 *             En cas de problème lors de l'accès à la BDD
	 */
	public void connectionDB() throws StockageException {
		ResourceBundle jdbcProperties = ResourceBundle.getBundle("jdbc");
		try {
			Class.forName(jdbcProperties.getString("jdbc.driver"));
			databaseConnection = DriverManager.getConnection(jdbcProperties.getString("jdbc.url"),
					jdbcProperties.getString("jdbc.user"), jdbcProperties.getString("jdbc.pw"));
			dataSchema = jdbcProperties.getString("jdbc.data.schema");
		} catch (ClassNotFoundException e) {
			throw new StockageException("Problème lors de la connection à la base de données");
		} catch (SQLException e) {
			throw new StockageException("Problème lors de la connection à la base de données\n"+e.getMessage());
		}
	}

	/**
	 * Fermer la connection à la base de données
	 * 
	 * @throws StockageException
	 *             En cas de problème lors de l'accès à la BDD
	 */
	public void closeConnection() throws StockageException {
		try {
			databaseConnection.close();
			databaseConnection = null;
		} catch (SQLException e) {
			throw new StockageException("Problème lors de la déconnection à la base de données");
		}
	}

	/**
	 * Pour savoir si la base de données est connectée
	 * 
	 * @return true si connectée
	 */
	public boolean isConnected() {
		return databaseConnection != null;
	}

	/**
	 * Récupère le tableau des pizzas stocké en BDD
	 * 
	 * @throws StockageException
	 *             En cas de problème lors de l'accès à la BDD
	 */
	private List<Pizza> getFromDB() throws StockageException {
		if (databaseConnection == null) {
			throw new StockageException("La base de données n'est pas connectée");
		}

		List<Pizza> menuPizzas = new ArrayList<>();
		Statement statement = null;
		ResultSet results = null;
		try {
			statement = databaseConnection.createStatement();
			results = statement.executeQuery("SELECT * FROM "+dataSchema+".pizza;");
			while (results.next()) {
				menuPizzas.add(new Pizza(results.getString("pizza_code"), results.getString("pizza_nom"),
						results.getFloat("pizza_prix"), CategoriePizza.valueOf(results.getString("pizza_categorie"))));
			}
		} catch (SQLException e) {
			throw new StockageException("Problème lors de la connection à la base de données");

		} finally {
			if (results != null) {
				try {
					results.close();
				} catch (SQLException e) {
					throw new StockageException("Problème lors de la connection à la base de données");
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					throw new StockageException("Problème lors de la connection à la base de données");
				}
			}
		}
		
		return menuPizzas;
	}

	@Override
	public List<Pizza> findAllPizzas() throws StockageException {
		return getFromDB();
	}

	@Override
	public void saveNewPizza(Pizza pizza) throws StockageException {
		if (databaseConnection == null) {
			throw new StockageException("La base de données n'est pas connectée");
		}
		if(!PizzaValidator.pizzaValide(pizza)) {
			throw new SavePizzaException("Pizza non valide");
		}

		PreparedStatement statement = null;
		try {
			statement = databaseConnection.prepareStatement(
					"INSERT INTO "+dataSchema+".pizza (pizza_code, pizza_nom, pizza_categorie, pizza_prix) VALUES (?, ?, ?, ?);");
			statement.setString(1, pizza.getCode());
			statement.setString(2, pizza.getNom());
			statement.setString(3, pizza.getCategorie().toString());
			statement.setDouble(4, pizza.getPrix());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new StockageException("Problème lors de l'ajout d'une pizza à la base de données");

		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					throw new StockageException("Problème lors de l'ajout d'une pizza à la base de données");
				}
			}
		}
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) throws StockageException {
		if (databaseConnection == null) {
			throw new StockageException("La base de données n'est pas connectée");
		}
		if(!PizzaValidator.pizzaValide(pizza)) {
			throw new UpdatePizzaException("Pizza non valide");
		}
		
		PreparedStatement statement = null;
		try {
			statement = databaseConnection.prepareStatement(
					"UPDATE "+dataSchema+".pizza SET pizza_code=?, pizza_nom=?, pizza_categorie=?, pizza_prix=? WHERE pizza_code=?;");
			statement.setString(1, pizza.getCode());
			statement.setString(2, pizza.getNom());
			statement.setString(3, pizza.getCategorie().toString());
			statement.setDouble(4, pizza.getPrix());
			statement.setString(5, codePizza);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new StockageException("Problème lors de la mise à jour d'une pizza dans la base de données");

		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					throw new StockageException("Problème lors de la mise à jour d'une pizza dans la base de données");
				}
			}
		}
	}

	@Override
	public void deletePizza(String codePizza) throws StockageException {
		if (databaseConnection == null) {
			throw new StockageException("La base de données n'est pas connectée");
		}

		Statement statement = null;
		try {
			statement = databaseConnection.createStatement();
			statement.executeUpdate("DELETE FROM "+dataSchema+".pizza WHERE pizza_code = '" + codePizza + "';");
		} catch (SQLException e) {
			throw new StockageException("Problème lors de la suppression d'une pizza à la base de données");

		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					throw new StockageException("Problème lors de la suppression d'une pizza à la base de données");
				}
			}
		}
	}

}
