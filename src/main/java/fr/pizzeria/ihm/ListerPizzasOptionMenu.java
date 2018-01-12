package fr.pizzeria.ihm;

import static fr.pizzeria.console.PizzeriaAdminConsoleApp.LOG;
import java.util.List;
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
		libelle = "Lister les pizzas";
	}

	@Override
	public void execute() {
		LOG.info("Liste des pizzas");
		List<Pizza> menu = dao.findAllPizzas();
		for (Pizza element : menu) {
			LOG.info(element.toString());
		}
	}

}
