package fr.pizzeria.ihm;

import static fr.pizzeria.console.PizzeriaAdminConsoleApp.LOG;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoDB;
import fr.pizzeria.exception.StockageException;

public class MenuConnectionDB extends OptionMenu {

	public MenuConnectionDB(Scanner scan, IPizzaDao dao) {
		super(scan, dao);
		libelle = "Se connecter � la base de donn�es";
	}

	public void closeDB() {
		PizzaDaoDB daoDB = (PizzaDaoDB) dao;
		if (daoDB.isConnected()) {
			try {
				daoDB.closeConnection();
			} catch (StockageException e) {
				LOG.info("Probl�me lors de la fermeture de la connection");
			}
		}
	}

	@Override
	public void execute() throws StockageException {
		PizzaDaoDB daoDB = (PizzaDaoDB) dao;
		if (daoDB.isConnected()) {
			LOG.info("BDD d�j� connect�e.");
			return;
		}

		LOG.info("Connection � la BDD...");
		daoDB.connectionDB();
		LOG.info("Connection r�ussie");
	}

}
