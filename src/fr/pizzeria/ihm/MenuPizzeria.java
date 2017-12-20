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
	
	public MenuPizzeria() {
		super();
		actions = new OptionMenu[4];
		actions[0] = new ListerPizzasOptionMenu();
		actions[1] = new AjouterPizzaOptionMenu();
		actions[2] = new ModifierPizzaOptionMenu();
		actions[3] = new SupprimerPizzaOptionMenu();
	}

	/**
	 * Affiche en console les options en lignes de commande
	 */
	public void afficher() {
		System.out.println("***** Pizzeria Administration *****");
		int indice = 1;
		for(OptionMenu courant : actions) {
			System.out.println(indice+". "+courant.getLibelle());
			indice++;
		}
		System.out.println("99. Sortir");
	}

	/**
	 * Exécute une option du menu
	 * @param indice
	 * @param menuTable
	 * @param scan
	 * @return
	 */
	public boolean executeMenu(int indice, IPizzaDao menuTable, Scanner scan) throws StockageException {
		return actions[indice-1].execute(menuTable, scan);
	}
}
