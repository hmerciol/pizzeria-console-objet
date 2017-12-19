package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.model.Pizza;

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

	public void afficher() {
		System.out.println("***** Pizzeria Administration *****");
		int indice = 1;
		for(OptionMenu courant : actions) {
			System.out.println(indice+". "+courant.getLibelle());
			indice++;
		}
		System.out.println("99. Sortir");
	}

	public Pizza[] executeMenu(int indice, Pizza[] menuTable, Scanner scan) {
		return actions[indice-1].execute(menuTable, scan);
	}
}
