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

	public ListerPizzasOptionMenu(Scanner scan, IPizzaDao dao) {
		super(scan, dao);
		libelle="Lister les pizzas";
	}

	@Override
	public void execute() {
		System.out.println("Liste des pizzas");
		DecimalFormat formatter = new DecimalFormat("#.00");
		Pizza[] menu = dao.findAllPizzas();
		for (Pizza element : menu) {
			System.out.println(
					element.getCode() + " -> " + element.getNom() + " (" + formatter.format(element.getPrix()) + " €)");
		}
	}

}
