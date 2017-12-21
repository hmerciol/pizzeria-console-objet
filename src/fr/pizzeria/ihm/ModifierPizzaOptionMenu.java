package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * @author hmerciol
 *
 */
public class ModifierPizzaOptionMenu extends OptionMenu {

	public ModifierPizzaOptionMenu(Scanner scan, IPizzaDao dao) {
		super(scan, dao);
		libelle = "Mettre � jour une pizza";
	}

	@Override
	public void execute() throws UpdatePizzaException {
		System.out.println("Mise � jour d\'une pizza");
		System.out.println("Veuillez choisir la pizza � modifier.");
		System.out.println("(99 pour abandonner).");

		String codeOld = scan.next();

		if (codeOld.equals("99"))
			return;

		System.out.println("Veuillez saisir le code");
		String code = scan.next();
		System.out.println("Veuillez saisir le nom (sans espace)");
		String nom = scan.next();
		System.out.println("Veuillez saisir la cat�gorie (avec \'_\' pour les espaces)");
		String categorie = scan.next().toUpperCase();
		System.out.println("Veuillez saisir le prix");
		double prix = Double.parseDouble(scan.next());

		dao.updatePizza(codeOld, new Pizza(code, nom, prix, CategoriePizza.valueOf(categorie)));
	}

}
