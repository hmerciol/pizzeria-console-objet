package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;

/**
 * @author hmerciol
 *
 */
public abstract class OptionMenu {

	/**
	 * Libell� de l'option menu
	 */
	protected String libelle;

	/**
	 * R�f�rence vers le scanner de la console
	 */
	protected Scanner scan;

	/**
	 * R�f�rence vers le gestionnaire du menu des pizzas
	 */
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
	 *             Envoy� en cas de mauvaise manipulation du menu des pizzas
	 */
	public abstract void execute() throws StockageException;
}
