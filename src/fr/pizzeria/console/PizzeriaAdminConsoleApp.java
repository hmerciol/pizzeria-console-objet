package fr.pizzeria.console;

import java.util.ArrayList;
import java.util.Scanner;

import fr.pizzeria.dao.*;
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
		
		// méthode additive
		// choisir une des deux lignes ci-dessous
		// IPizzaDao menuPizzeria = new PizzaDaoImpl();
		// IPizzaDao menuPizzeria = new PizzaDaoFichier();
		/* 
		 * try {
		 * menuPizzeria.saveNewPizza(new Pizza("PEP", "Pépéroni", 12.50, CategoriePizza.VIANDE));
		 * menuPizzeria.saveNewPizza(new Pizza("MAR", "Margherita", 14.00, CategoriePizza.VIANDE));
		 * menuPizzeria.saveNewPizza(new Pizza("REIN", "La Reine", 11.50, CategoriePizza.VIANDE));
		 * menuPizzeria.saveNewPizza(new Pizza("FRO", "La 4 fromages", 12.00, CategoriePizza.SANS_VIANDE));
		 * menuPizzeria.saveNewPizza(new Pizza("CAN", "La cannibale", 12.50, CategoriePizza.VIANDE));
		 * menuPizzeria.saveNewPizza(new Pizza("SAV", "La savoyarde", 13.00, CategoriePizza.VIANDE));
		 * menuPizzeria.saveNewPizza(new Pizza("ORI", "L\'orientale", 13.50, CategoriePizza.VIANDE));
		 * menuPizzeria.saveNewPizza(new Pizza("IND", "L\'indienne", 14.00, CategoriePizza.VIANDE));
		 * }
		 * catch (StockageException e) {
		 * System.out.println("Erreur lors de l'initialisation du menu");
		 * System.out.println(e.getMessage());
		 * on = false;
		 * }
		 */
		
		// méthode sans try and catch bloc
		ArrayList<Pizza> menuTable = new ArrayList<Pizza>();
		menuTable.add(new Pizza("PEP", "Pépéroni", 12.50, CategoriePizza.VIANDE));
		menuTable.add(new Pizza("MAR", "Margherita", 14.00, CategoriePizza.VIANDE));
		menuTable.add(new Pizza("REIN", "La Reine", 11.50, CategoriePizza.VIANDE));
		menuTable.add(new Pizza("FRO", "La 4 fromages", 12.00, CategoriePizza.SANS_VIANDE));
		menuTable.add(new Pizza("CAN", "La cannibale", 12.50, CategoriePizza.VIANDE));
		menuTable.add(new Pizza("SAV", "La savoyarde", 13.00, CategoriePizza.VIANDE));
		menuTable.add(new Pizza("ORI", "L\'orientale", 13.50, CategoriePizza.VIANDE));
		menuTable.add(new Pizza("IND", "L\'indienne", 14.00, CategoriePizza.VIANDE));
		
		// choisir une des deux lignes ci-dessous
		// IPizzaDao menuPizzeria = new PizzaDaoImpl(menuTable);
		IPizzaDao menuPizzeria = new PizzaDaoFichier(menuTable); //version avec accès fichier

		MenuPizzeria console = new MenuPizzeria(scan, menuPizzeria);
		console.demarreConsole();

		scan.close();
	}

}
