package fr.pizzeria.ihm;

import java.text.DecimalFormat;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.Pizza;

/**
 * @author hmerciol
 *
 */
public class ListerPizzasOptionMenu extends OptionMenu {

	@Override
	public String getLibelle() {
		return "Lister les pizzas";
	}

	@Override
	public boolean execute(IPizzaDao menuPizzeria, Scanner scan) {
		System.out.println("Liste des pizzas");
		DecimalFormat formatter = new DecimalFormat("#.00");
		Pizza[] menuPizza = menuPizzeria.findAllPizzas();
		for (Pizza element : menuPizza) {
			System.out.println(
					element.getCode() + " -> " + element.getNom() + " (" + formatter.format(element.getPrix()) + " €)");
		}
		return true;
	}

}
