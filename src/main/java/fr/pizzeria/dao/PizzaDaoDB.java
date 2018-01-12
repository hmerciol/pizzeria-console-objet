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

import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

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
		} catch (ClassNotFoundException e) {
			throw new StockageException("Probl�me lors de la connection � la base de donn�es");
		} catch (SQLException e) {
			throw new StockageException("Probl�me lors de la connection � la base de donn�es");
		}
		getFromDB();
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
	 * R�cup�re le tableau des pizzas stock� en BDD
	 * 
	 * @throws StockageException
	 *             En cas de probl�me lors de l'acc�s � la BDD
	 */
	private List<Pizza> getFromDB() throws StockageException {
		if (databaseConnection == null) {
			throw new StockageException("La base de donn�es n'est pas connect�e");
		}

		List<Pizza> menuPizzas = new ArrayList<>();
		Statement statement = null;
		ResultSet results = null;
		try {
			statement = databaseConnection.createStatement();
			results = statement.executeQuery("SELECT * FROM pizzeria.pizza;");
			while (results.next()) {
				menuPizzas.add(new Pizza(results.getString("pizza_code"), results.getString("pizza_nom"),
						results.getFloat("pizza_prix"), CategoriePizza.valueOf(results.getString("pizza_categorie"))));
			}
		} catch (SQLException e) {
			throw new StockageException("Probl�me lors de la connection � la base de donn�es");

		} finally {
			if (results != null) {
				try {
					results.close();
				} catch (SQLException e) {
					throw new StockageException("Probl�me lors de la connection � la base de donn�es");
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					throw new StockageException("Probl�me lors de la connection � la base de donn�es");
				}
			}
		}
		
		return menuPizzas;
	}

	/**
	 * Stocke une pizza sur la BDD
	 * 
	 * @param pizza
	 *            La pizza � stocker
	 * @throws StockageException
	 *             En cas de probl�me lors de l'acc�s � la BDD
	 */
	private void saveInDB(Pizza pizza) throws StockageException {
		if (databaseConnection == null) {
			throw new StockageException("La base de donn�es n'est pas connect�e");
		}

		PreparedStatement statement = null;
		try {
			statement = databaseConnection.prepareStatement(
					"INSERT INTO pizzeria.pizza (pizza_code, pizza_nom, pizza_categorie, pizza_prix) VALUES (?, ?, ?, ?);");
			statement.setString(1, pizza.getCode());
			statement.setString(2, pizza.getNom());
			statement.setString(3, pizza.getCategorie().toString());
			statement.setDouble(4, pizza.getPrix());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new StockageException("Probl�me lors de l'ajout d'une pizza � la base de donn�es");

		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					throw new StockageException("Probl�me lors de l'ajout d'une pizza � la base de donn�es");
				}
			}
		}
	}

	/**
	 * Supprime une pizza sur la BDD
	 * 
	 * @param codePizza
	 *            Le code de la pizza � supprimer
	 * @throws StockageException
	 *             En cas de probl�me lors de l'acc�s � la BDD
	 */
	private void deleteInDB(String codePizza) throws StockageException {
		if (databaseConnection == null) {
			throw new StockageException("La base de donn�es n'est pas connect�e");
		}

		Statement statement = null;
		try {
			statement = databaseConnection.createStatement();
			statement.executeUpdate("DELETE FROM pizzeria.pizza WHERE pizza_code = '" + codePizza + "';");
		} catch (SQLException e) {
			throw new StockageException("Probl�me lors de la suppression d'une pizza � la base de donn�es");

		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					throw new StockageException("Probl�me lors de la suppression d'une pizza � la base de donn�es");
				}
			}
		}
	}

	@Override
	public List<Pizza> findAllPizzas() throws StockageException {
		return getFromDB();
	}

	@Override
	public void saveNewPizza(Pizza pizza) throws StockageException {
		saveInDB(pizza);
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) throws StockageException {
		deleteInDB(codePizza);
		saveInDB(pizza);
	}

	@Override
	public void deletePizza(String codePizza) throws StockageException {
		deleteInDB(codePizza);
	}

}
