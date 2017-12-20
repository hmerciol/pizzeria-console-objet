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
	 * Pour obtenir le libellé d'une option dans le menu
	 * @return libelle
	 */
	public abstract String getLibelle();
	
	/**
	 * Pour exécuter la commande corespondante
	 * @param menuPizzeria
	 * @param scan
	 * @return
	 */
	public abstract boolean execute(IPizzaDao menuPizzeria, Scanner scan) throws StockageException;
}
