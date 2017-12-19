package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.Pizza;

public class AjouterPizzaOptionMenu extends OptionMenu {

	@Override
	public String getLibelle() {
		return "Ajouter une nouvelle pizza";
	}

	@Override
	public boolean execute(IPizzaDao menuPizzeria, Scanner scan) {
		System.out.println("Ajout d\'une nouvelle pizza");
		System.out.println("Veuillez saisir le code");
		String code = scan.next();
		System.out.println("Veuillez saisir le nom (sans espace)");
		String nom = scan.next();
		System.out.println("Veuillez saisir le prix");
		double prix = scan.nextDouble();

		return menuPizzeria.saveNewPizza(new Pizza(code, nom, prix));
		}

}
