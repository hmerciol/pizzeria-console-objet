package fr.pizzeria.console;

import java.util.ArrayList;
import java.util.Scanner;
import fr.pizzeria.model.*;

public class PizzeriaAdminConsoleApp {

	public static void main(String[] args) {
		boolean on = true;
		Scanner scan = new Scanner(System.in);
		int instruction;
		
		//instanciation du tableau des pizzas de base
		int idPizzas = 0;
		ArrayList<Pizza> menu = new ArrayList<Pizza>();
		menu.add(new Pizza(idPizzas++, "PEP", "Pépéroni", 12.50));
		menu.add(new Pizza(idPizzas++, "MAR", "Margherita", 14.00));
		menu.add(new Pizza(idPizzas++, "REIN", "La Reine", 11.50));
		menu.add(new Pizza(idPizzas++, "FRO", "La 4 fromages", 12.00));
		menu.add(new Pizza(idPizzas++, "CAN", "La cannibale", 12.50));
		menu.add(new Pizza(idPizzas++, "SAV", "La savoyarde", 13.00));
		menu.add(new Pizza(idPizzas++, "ORI", "L\'orientale", 13.50));
		menu.add(new Pizza(idPizzas++, "IND", "L\'indienne", 14.00));
		
		while(on) {
			System.out.println("***** Pizzeria Administration *****");
			System.out.println("1. Lister les pizzas");
			System.out.println("2. Ajouter une nouvelle pizza");
			System.out.println("3. Mettre à jour une pizza");
			System.out.println("4. Supprimer une pizza");
			System.out.println("99. Sortir");
			System.out.println("Sélectionner une action :");
			instruction = scan.nextInt();
			
			switch (instruction) {
			case 1:
				System.out.println("Liste des pizzas");
				afficheListe(menu);
				break;
			case 2:
				System.out.println("Ajout d\'une nouvelle pizza");
				break;
			case 3:
				System.out.println("Mise à jour d\'une pizza");
				break;
			case 4:
				System.out.println("Suppression d\'une pizza");
				break;
			case 99:
				System.out.println("Au revoir :(");
				on = false;
				break;
			default:
				System.out.println("Commande incorrecte");
				break;
			}
		}
		
		scan.close();
	}
	
	public static void afficheListe(ArrayList<Pizza> menu) {
		for(Pizza element : menu) {
			System.out.println(element.getCode()+" -> "+element.getNom()+" ("+element.getPrix()+" €)");
		}
	}

}
