package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;

/**
 * @author hmerciol
 *
 */
public abstract class OptionMenu {

	protected String libelle;
	protected Scanner scan;
	protected IPizzaDao dao;

	public OptionMenu(Scanner scan, IPizzaDao dao) {
		super();
		this.scan = scan;
		this.dao = dao;
	}

	/**
	 * Pour obtenir le libell� d'une option dans le menu
	 * 
	 * @return libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * Pour ex�cuter la commande corespondante
	 * 
	 * @throws StockageException
	 */
	public abstract void execute() throws StockageException;
}
