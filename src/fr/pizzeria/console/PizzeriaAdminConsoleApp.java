package fr.pizzeria.console;

import java.util.ArrayList;
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
		Scanner scan = new Scanner(System.in);

		// instanciation du tableau des pizzas de base
		/*
		 * PizzaDaoImpl menuPizzeria = new PizzaDaoImpl(); try {
		 * menuPizzeria.saveNewPizza(new Pizza("PEP", "Pépéroni", 12.50));
		 * menuPizzeria.saveNewPizza(new Pizza("MAR", "Margherita", 14.00));
		 * menuPizzeria.saveNewPizza(new Pizza("REIN", "La Reine", 11.50));
		 * menuPizzeria.saveNewPizza(new Pizza("FRO", "La 4 fromages", 12.00));
		 * menuPizzeria.saveNewPizza(new Pizza("CAN", "La cannibale", 12.50));
		 * menuPizzeria.saveNewPizza(new Pizza("SAV", "La savoyarde", 13.00));
		 * menuPizzeria.saveNewPizza(new Pizza("ORI", "L\'orientale", 13.50));
		 * menuPizzeria.saveNewPizza(new Pizza("IND", "L\'indienne", 14.00));
		 * }
		 * catch (StockageException e) {
		 * System.out.println("Erreur lors de l'initialisation du menu");
		 * System.out.println(e.getMessage());
		 * on = false;
		 * }
		 */
		//version sans try and catch bloc
		ArrayList<Pizza> menuTable = new ArrayList<Pizza>();
		menuTable.add(new Pizza("PEP", "Pépéroni", 12.50, CategoriePizza.VIANDE));
		menuTable.add(new Pizza("MAR", "Margherita", 14.00, CategoriePizza.VIANDE));
		menuTable.add(new Pizza("REIN", "La Reine", 11.50, CategoriePizza.VIANDE));
		menuTable.add(new Pizza("FRO", "La 4 fromages", 12.00, CategoriePizza.SANS_VIANDE));
		menuTable.add(new Pizza("CAN", "La cannibale", 12.50, CategoriePizza.VIANDE));
		menuTable.add(new Pizza("SAV", "La savoyarde", 13.00, CategoriePizza.SANS_VIANDE));
		menuTable.add(new Pizza("ORI", "L\'orientale", 13.50, CategoriePizza.POISSON));
		menuTable.add(new Pizza("IND", "L\'indienne", 14.00, CategoriePizza.POISSON));
		PizzaDaoImpl menuPizzeria = new PizzaDaoImpl(menuTable);

		MenuPizzeria console = new MenuPizzeria(scan, menuPizzeria);
		console.demarreConsole();

		scan.close();
	}

}
