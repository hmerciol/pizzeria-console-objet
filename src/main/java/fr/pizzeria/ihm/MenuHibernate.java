package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoHibernate;
import fr.pizzeria.exception.StockageException;

public class MenuHibernate extends OptionMenu {

	public MenuHibernate(Scanner scan, IPizzaDao dao) {
		super(scan, dao);
		libelle = "Relancer la connection à la BDD";
	}
	
	public void closeHibernate() {
		PizzaDaoHibernate daoHb = (PizzaDaoHibernate) dao;
		daoHb.closeConnection();
	}

	@Override
	public void execute() throws StockageException {
		PizzaDaoHibernate daoHb = (PizzaDaoHibernate) dao;
		daoHb.resetConnection();
	}

}
