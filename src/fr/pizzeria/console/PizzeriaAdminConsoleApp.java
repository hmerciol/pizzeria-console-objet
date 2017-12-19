package fr.pizzeria.console;

import java.text.DecimalFormat;
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
		
		//console
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
				menu.add(nouvellePizza(scan, idPizzas));
				idPizzas++;
				break;
				
			case 3:
				System.out.println("Mise à jour d\'une pizza");
				afficheListe(menu);
				changerPizza(scan, menu);
				break;
				
			case 4:
				System.out.println("Suppression d\'une pizza");
				afficheListe(menu);
				retirerPizza(scan, menu);
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
	
	//affiche la liste des pizzas du menu
	public static void afficheListe(ArrayList<Pizza> menu) {
		for(Pizza element : menu) {
			DecimalFormat formatter = new DecimalFormat("#.00");
			System.out.println(element.getCode()+" -> "+element.getNom()+" ("+formatter.format(element.getPrix())+" €)");
		}
	}
	
	//création d'une nouvelle pizza
	public static Pizza nouvellePizza(Scanner scan, int id) {
		System.out.println("Veuillez saisir le code");
		String code = scan.next();
		System.out.println("Veuillez saisir le nom (sans espace)");
		String nom = scan.next();
		System.out.println("Veuillez saisir le prix");
		double prix = scan.nextDouble();
		
		return new Pizza(id, code, nom, prix);
	}
	
	//mise à jour d'une pizza
	public static void changerPizza(Scanner scan, ArrayList<Pizza> menu) {
		System.out.println("Veuillez choisir la pizza à modifier.");
		System.out.println("(99 pour abandonner).");
		
		String codeOld = scan.next();
		
		for(Pizza courant : menu) {
			if(codeOld.equals(courant.getCode())) {
				System.out.println("Veuillez saisir le code");
				courant.setCode(scan.next());
				System.out.println("Veuillez saisir le nom (sans espace)");
				courant.setNom(scan.next());
				System.out.println("Veuillez saisir le prix");
				courant.setPrix(scan.nextDouble());
				return;
			}
		}
		
		System.out.println("Pizza non trouvée");
	}
	
	//suppression d'une pizza
	public static void retirerPizza(Scanner scan, ArrayList<Pizza> menu) {
		System.out.println("Veuillez choisir la pizza à supprimer.");
		System.out.println("(99 pour abandonner).");
		
		String codeSuppr = scan.next();
		
		for(Pizza courant : menu) {
			if(codeSuppr.equals(courant.getCode())) {
				menu.remove(courant);
				return;
			}
		}
		
		System.out.println("Pizza non trouvée");
	}

}
