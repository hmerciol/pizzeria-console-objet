package fr.pizzeria.ihm;

import static fr.pizzeria.console.PizzeriaAdminConsoleApp.LOG;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoDB;
import fr.pizzeria.exception.StockageException;

public class MenuConnectionDB extends OptionMenu {

	public MenuConnectionDB(Scanner scan, IPizzaDao dao) {
		super(scan, dao);
		libelle = "Se connecter à la base de données";
	}

	public void closeDB() {
		PizzaDaoDB daoDB = (PizzaDaoDB) dao;
		if (daoDB.isConnected()) {
			try {
				daoDB.closeConnection();
			} catch (StockageException e) {
				LOG.info("Problème lors de la fermeture de la connection");
			}
		}
	}

	@Override
	public void execute() throws StockageException {
		PizzaDaoDB daoDB = (PizzaDaoDB) dao;
		if (daoDB.isConnected()) {
			LOG.info("BDD déjà connectée.");
			return;
		}

		LOG.info("Connection à la BDD...");
		daoDB.connectionDB();
		LOG.info("Connection réussie");
	}

}
