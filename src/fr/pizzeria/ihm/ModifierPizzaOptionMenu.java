package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.UpdatePizzaException;
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
	public boolean execute(IPizzaDao menuPizzeria, Scanner scan) throws UpdatePizzaException {
		System.out.println("Mise à jour d\'une pizza");
		System.out.println("Veuillez choisir la pizza à modifier.");
		System.out.println("(99 pour abandonner).");

		String codeOld = scan.next();

		if (codeOld.equals("99"))
			return false;

		System.out.println("Veuillez saisir le code");
		String code = scan.next();
		System.out.println("Veuillez saisir le nom (sans espace)");
		String nom = scan.next();
		System.out.println("Veuillez saisir le prix");
		double prix = scan.nextDouble();

		return menuPizzeria.updatePizza(codeOld, new Pizza(code, nom, prix));
	}

}
