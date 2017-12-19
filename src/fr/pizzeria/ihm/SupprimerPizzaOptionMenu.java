package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.model.Pizza;

/**
 * @author hmerciol
 *
 */
public class SupprimerPizzaOptionMenu extends OptionMenu {

	@Override
	public String getLibelle() {
		return "Supprimer une pizza";
	}

	@Override
	public Pizza[] execute(Pizza[] menuTable, Scanner scan) {
		System.out.println("Suppression d\'une pizza");
		System.out.println("Veuillez choisir la pizza à supprimer.");
		System.out.println("(99 pour abandonner).");

		String codeSuppr = scan.next();

		if (codeSuppr.equals("99"))
			return menuTable;

		Pizza[] newMenu = new Pizza[menuTable.length - 1];
		boolean removed = false;

		for (int i = 0; i < menuTable.length; i++) {
			if (codeSuppr.equals(menuTable[i].getCode())) {
				removed = true;
				continue;
			}
			newMenu[removed ? i - 1 : i] = menuTable[i];
		}

		return newMenu;
	}

}
