package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.model.Pizza;

/**
 * @author hmerciol
 *
 */
public class ModifierPizzaOptionMenu extends OptionMenu {

	@Override
	public String getLibelle() {
		return "Mettre à jour une pizza";
	}

	@Override
	public boolean execute(Pizza[] menuTable, Scanner scan) {
		System.out.println("Mise à jour d\'une pizza");
		System.out.println("Veuillez choisir la pizza à modifier.");
		System.out.println("(99 pour abandonner).");

		String codeOld = scan.next();

		if (codeOld.equals("99"))
			return false;

		boolean trouve = false;
		for (Pizza courant : menuTable) {
			if (codeOld.equals(courant.getCode())) {
				System.out.println("Veuillez saisir le code");
				courant.setCode(scan.next());
				System.out.println("Veuillez saisir le nom (sans espace)");
				courant.setNom(scan.next());
				System.out.println("Veuillez saisir le prix");
				courant.setPrix(scan.nextDouble());
				trouve = true;
			}
		}

		return trouve;
	}

}
