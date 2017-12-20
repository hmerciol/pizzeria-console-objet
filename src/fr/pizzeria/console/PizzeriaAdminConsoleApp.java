package fr.pizzeria.console;

import java.util.Scanner;

import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.exception.StockageException;
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

		// instanciation du tableau des pizzas de base
//		PizzaDaoImpl menuPizzeria = new PizzaDaoImpl();
//		try {
//			menuPizzeria.saveNewPizza(new Pizza("PEP", "Pépéroni", 12.50));
//			menuPizzeria.saveNewPizza(new Pizza("MAR", "Margherita", 14.00));
//			menuPizzeria.saveNewPizza(new Pizza("REIN", "La Reine", 11.50));
//			menuPizzeria.saveNewPizza(new Pizza("FRO", "La 4 fromages", 12.00));
//			menuPizzeria.saveNewPizza(new Pizza("CAN", "La cannibale", 12.50));
//			menuPizzeria.saveNewPizza(new Pizza("SAV", "La savoyarde", 13.00));
//			menuPizzeria.saveNewPizza(new Pizza("ORI", "L\'orientale", 13.50));
//			menuPizzeria.saveNewPizza(new Pizza("IND", "L\'indienne", 14.00));
//		} catch (StockageException e) {
//			System.out.println("Erreur lors de l'initialisation du menu");
//			System.out.println(e.getMessage());
//			on = false;
//		}
		Pizza[] menuTable = new Pizza[8];
		menuTable[0] = (new Pizza("PEP", "Pépéroni", 12.50));
		menuTable[1] = (new Pizza("MAR", "Margherita", 14.00));
		menuTable[2] = (new Pizza("REIN", "La Reine", 11.50));
		menuTable[3] = (new Pizza("FRO", "La 4 fromages", 12.00));
		menuTable[4] = (new Pizza("CAN", "La cannibale", 12.50));
		menuTable[5] = (new Pizza("SAV", "La savoyarde", 13.00));
		menuTable[6] = (new Pizza("ORI", "L\'orientale", 13.50));
		menuTable[7] = (new Pizza("IND", "L\'indienne", 14.00));
		PizzaDaoImpl menuPizzeria = new PizzaDaoImpl(menuTable);
		
		MenuPizzeria console = new MenuPizzeria(scan, menuPizzeria);

		// console
		while (on) {
			console.afficher();
			instruction = scan.nextInt();
			if (instruction == 99) {
				System.out.println("Au revoir :(");
				on = false;
			} else {
				try {
					console.executeMenu(instruction);
				} catch (StockageException e) {
					System.out.println(e.getMessage());
				}
			}
		}

		scan.close();
	}

}
