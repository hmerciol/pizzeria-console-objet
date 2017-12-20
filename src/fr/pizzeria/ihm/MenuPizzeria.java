package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;

/**
 * @author hmerciol
 *
 */
public class MenuPizzeria {

	private OptionMenu[] actions;

	public MenuPizzeria(Scanner scan, IPizzaDao dao) {
		super();
		actions = new OptionMenu[4];
		actions[0] = new ListerPizzasOptionMenu(scan, dao);
		actions[1] = new AjouterPizzaOptionMenu(scan, dao);
		actions[2] = new ModifierPizzaOptionMenu(scan, dao);
		actions[3] = new SupprimerPizzaOptionMenu(scan, dao);
	}

	/**
	 * Affiche en console les options en lignes de commande
	 */
	public void afficher() {
		System.out.println("***** Pizzeria Administration *****");
		int indice = 1;
		for (OptionMenu courant : actions) {
			System.out.println(indice + ". " + courant.getLibelle());
			indice++;
		}
		System.out.println("99. Sortir");
	}

	/**
	 * Exécute une option du menu
	 * @param indice
	 * @throws StockageException
	 */
	public void executeMenu(int indice) throws StockageException {
		actions[indice - 1].execute();
	}
}
