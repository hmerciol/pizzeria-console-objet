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
	 * Libellé de l'option menu
	 */
	protected String libelle;

	/**
	 * Référence vers le scanner de la console
	 */
	protected Scanner scan;

	/**
	 * Référence vers le gestionnaire du menu des pizzas
	 */
	protected IPizzaDao dao;

	public OptionMenu(Scanner scan, IPizzaDao dao) {
		super();
		this.scan = scan;
		this.dao = dao;
	}

	/**
	 * Pour obtenir le libellé d'une option dans le menu
	 * 
	 * @return libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * Pour exécuter la commande corespondante
	 * 
	 * @throws StockageException
	 *             Envoyé en cas de mauvaise manipulation du menu des pizzas
	 */
	public abstract void execute() throws StockageException;
}
