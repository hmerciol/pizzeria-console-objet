package fr.pizzeria.console;

import java.util.Scanner;

import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
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
		try {
			menuPizzeria.saveNewPizza(new Pizza("PEP", "Pépéroni", 12.50));
			menuPizzeria.saveNewPizza(new Pizza("MAR", "Margherita", 14.00));
			menuPizzeria.saveNewPizza(new Pizza("REIN", "La Reine", 11.50));
			menuPizzeria.saveNewPizza(new Pizza("FRO", "La 4 fromages", 12.00));
			menuPizzeria.saveNewPizza(new Pizza("CAN", "La cannibale", 12.50));
			menuPizzeria.saveNewPizza(new Pizza("SAV", "La savoyarde", 13.00));
			menuPizzeria.saveNewPizza(new Pizza("ORI", "L\'orientale", 13.50));
			menuPizzeria.saveNewPizza(new Pizza("IND", "L\'indienne", 14.00));
		} catch (SavePizzaException e) {
			System.out.println("Erreur lors de l'initialisation du menu");
		}

		// console
		while (on) {
			console.afficher();
			instruction = scan.nextInt();
			if (instruction == 99) {
				System.out.println("Au revoir :(");
				on = false;
			} else {
				try {
					console.executeMenu(instruction, menuPizzeria, scan);
				} catch (SavePizzaException e) {
					System.out.println("Erreur lors de l'enregistrement de la pizza");
				} catch (UpdatePizzaException e) {
					System.out.println("Erreur lors de la mise à jour de la pizza");
				} catch (DeletePizzaException e) {
					System.out.println("Erreur lors de la suppression de la pizza");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		scan.close();
	}

}
