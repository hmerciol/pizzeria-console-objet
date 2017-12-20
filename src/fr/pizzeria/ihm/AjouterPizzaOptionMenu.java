package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.model.Pizza;

/**
 * @author hmerciol
 *
 */
public class AjouterPizzaOptionMenu extends OptionMenu {

	public AjouterPizzaOptionMenu(Scanner scan, IPizzaDao dao) {
		super(scan, dao);
		libelle = "Ajouter une nouvelle pizza";
	}

	@Override
	public void execute() throws SavePizzaException {
		System.out.println("Ajout d\'une nouvelle pizza");
		System.out.println("Veuillez saisir le code");
		String code = scan.next();
		System.out.println("Veuillez saisir le nom (sans espace)");
		String nom = scan.next();
		System.out.println("Veuillez saisir le prix");
		double prix = scan.nextDouble();

		dao.saveNewPizza(new Pizza(code, nom, prix));
	}

}
