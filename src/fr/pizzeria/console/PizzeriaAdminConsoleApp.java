package fr.pizzeria.console;

import java.util.Scanner;

import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.ihm.MenuPizzeria;
import fr.pizzeria.model.*;

/**
 * @author hmerciol
 *
 */
public class PizzeriaAdminConsoleApp {

	public static void main(String[] args) {
		boolean on = true;
		Scanner scan = new Scanner(System.in);
		int instruction;
		MenuPizzeria console = new MenuPizzeria();

		// instanciation du tableau des pizzas de base
		PizzaDaoImpl menuPizzeria = new PizzaDaoImpl();
		menuPizzeria.saveNewPizza(new Pizza("PEP", "Pépéroni", 12.50));
		menuPizzeria.saveNewPizza(new Pizza("MAR", "Margherita", 14.00));
		menuPizzeria.saveNewPizza(new Pizza("REIN", "La Reine", 11.50));
		menuPizzeria.saveNewPizza(new Pizza("FRO", "La 4 fromages", 12.00));
		menuPizzeria.saveNewPizza(new Pizza("CAN", "La cannibale", 12.50));
		menuPizzeria.saveNewPizza(new Pizza("SAV", "La savoyarde", 13.00));
		menuPizzeria.saveNewPizza(new Pizza("ORI", "L\'orientale", 13.50));
		menuPizzeria.saveNewPizza(new Pizza("IND", "L\'indienne", 14.00));

		// console
		while (on) {
			console.afficher();
			instruction = scan.nextInt();
			if(instruction==99) {
				System.out.println("Au revoir :(");
				on = false;
			}else {
				console.executeMenu(instruction, menuPizzeria, scan);
			}
		}

		scan.close();
	}

}
