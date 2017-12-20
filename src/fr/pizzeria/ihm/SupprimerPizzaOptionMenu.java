package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.DeletePizzaException;

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
	public boolean execute(IPizzaDao menuPizzeria, Scanner scan) throws DeletePizzaException {
		System.out.println("Suppression d\'une pizza");
		System.out.println("Veuillez choisir la pizza à supprimer.");
		System.out.println("(99 pour abandonner).");

		String codeSuppr = scan.next();

		if (codeSuppr.equals("99"))
			return false;

		return menuPizzeria.deletePizza(codeSuppr);
	}

}
