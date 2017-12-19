package fr.pizzeria.console;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
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
		Pizza.lastId = -1;

		// instanciation du tableau des pizzas de base

		// avec ArrayList :
		// ArrayList<Pizza> menu = new ArrayList<Pizza>();
		// menu.add(new Pizza("PEP", "Pépéroni", 12.50));
		// menu.add(new Pizza("MAR", "Margherita", 14.00));
		// menu.add(new Pizza("REIN", "La Reine", 11.50));
		// menu.add(new Pizza("FRO", "La 4 fromages", 12.00));
		// menu.add(new Pizza("CAN", "La cannibale", 12.50));
		// menu.add(new Pizza("SAV", "La savoyarde", 13.00));
		// menu.add(new Pizza("ORI", "L\'orientale", 13.50));
		// menu.add(new Pizza("IND", "L\'indienne", 14.00));

		// avec tableau
		Pizza[] menuTable = new Pizza[8];
		menuTable[0] = (new Pizza("PEP", "Pépéroni", 12.50));
		menuTable[1] = (new Pizza("MAR", "Margherita", 14.00));
		menuTable[2] = (new Pizza("REIN", "La Reine", 11.50));
		menuTable[3] = (new Pizza("FRO", "La 4 fromages", 12.00));
		menuTable[4] = (new Pizza("CAN", "La cannibale", 12.50));
		menuTable[5] = (new Pizza("SAV", "La savoyarde", 13.00));
		menuTable[6] = (new Pizza("ORI", "L\'orientale", 13.50));
		menuTable[7] = (new Pizza("IND", "L\'indienne", 14.00));

		// console
		while (on) {
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
				// afficheListe(menu);
				afficheListe(menuTable);
				break;

			case 2:
				System.out.println("Ajout d\'une nouvelle pizza");
				// menu.add(nouvellePizza(scan));	//avec ArrayList
				menuTable = nouvellePizza(scan, menuTable);	//avec tableaux
				break;

			case 3:
				System.out.println("Mise à jour d\'une pizza");
				// afficheListe(menu);	//avec ArrayList
				// changerPizza(scan, menu);	//avec ArrayList
				afficheListe(menuTable);	//avec tableaux
				changerPizza(scan, menuTable);	//avec tableaux
				break;

			case 4:
				System.out.println("Suppression d\'une pizza");
				// afficheListe(menu);	//avec ArrayList
				// retirerPizza(scan, menu);	//avec ArrayList
				afficheListe(menuTable);	//avec tableaux
				menuTable = retirerPizza(scan, menuTable);	//avec tableaux
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

	/**
	 * Affiche la liste (ArrayList) des pizzas du menu
	 * @param menu
	 */
	public static void afficheListe(ArrayList<Pizza> menu) {
		DecimalFormat formatter = new DecimalFormat("#.00");
		for (Pizza element : menu) {
			System.out.println(
					element.getCode() + " -> " + element.getNom() + " (" + formatter.format(element.getPrix()) + " €)");
		}
	}

	/**
	 * Affiche la liste (tableau Pizza[]) des pizzas du menu
	 * @param menuTable
	 */
	public static void afficheListe(Pizza[] menuTable) {
		DecimalFormat formatter = new DecimalFormat("#.00");
		for (Pizza element : menuTable) {
			System.out.println(
					element.getCode() + " -> " + element.getNom() + " (" + formatter.format(element.getPrix()) + " €)");
		}
	}

	/**
	 * Création d'une nouvelle pizza
	 * @param scan
	 * @return pizza
	 */
	public static Pizza nouvellePizza(Scanner scan) {
		System.out.println("Veuillez saisir le code");
		String code = scan.next();
		System.out.println("Veuillez saisir le nom (sans espace)");
		String nom = scan.next();
		System.out.println("Veuillez saisir le prix");
		double prix = scan.nextDouble();

		return new Pizza(code, nom, prix);
	}

	/**
	 * Augmentation d'un tableau de pizzas d'une nouvelle pizza
	 * @param scan
	 * @param menuTable
	 * @return menuTable
	 */
	public static Pizza[] nouvellePizza(Scanner scan, Pizza[] menuTable) {
		System.out.println("Veuillez saisir le code");
		String code = scan.next();
		System.out.println("Veuillez saisir le nom (sans espace)");
		String nom = scan.next();
		System.out.println("Veuillez saisir le prix");
		double prix = scan.nextDouble();

		Pizza[] newMenu = new Pizza[menuTable.length + 1];
		for (int i = 0; i < menuTable.length; i++) {
			newMenu[i] = menuTable[i];
		}
		newMenu[menuTable.length] = new Pizza(code, nom, prix);

		return newMenu;
	}

	/**
	 * Mise à jour d'une pizza dans une liste (ArrayList)
	 * @param scan
	 * @param menu
	 */
	public static void changerPizza(Scanner scan, ArrayList<Pizza> menu) {
		System.out.println("Veuillez choisir la pizza à modifier.");
		System.out.println("(99 pour abandonner).");

		String codeOld = scan.next();

		for (Pizza courant : menu) {
			if (codeOld.equals(courant.getCode())) {
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

	/**
	 * Mise à jour d'une pizza dans un tableau
	 * @param scan
	 * @param menuTable
	 */
	public static void changerPizza(Scanner scan, Pizza[] menuTable) {
		System.out.println("Veuillez choisir la pizza à modifier.");
		System.out.println("(99 pour abandonner).");

		String codeOld = scan.next();

		for (Pizza courant : menuTable) {
			if (codeOld.equals(courant.getCode())) {
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

	/**
	 * Suppression d'une pizza dans une liste (ArrayList)
	 * @param scan
	 * @param menu
	 */
	public static void retirerPizza(Scanner scan, ArrayList<Pizza> menu) {
		System.out.println("Veuillez choisir la pizza à supprimer.");
		System.out.println("(99 pour abandonner).");

		String codeSuppr = scan.next();

		for (Pizza courant : menu) {
			if (codeSuppr.equals(courant.getCode())) {
				menu.remove(courant);
				return;
			}
		}

		System.out.println("Pizza non trouvée");
	}

	/**
	 * Suppression d'une pizza dans un tableau
	 * @param scan
	 * @param menuTable
	 * @return menuTable
	 */
	public static Pizza[] retirerPizza(Scanner scan, Pizza[] menuTable) {
		System.out.println("Veuillez choisir la pizza à supprimer.");
		System.out.println("(99 pour abandonner).");

		String codeSuppr = scan.next();
		Pizza[] newMenu = new Pizza[menuTable.length - 1];
		boolean removed = false;

		for (int i = 0; i < menuTable.length; i++) {
			if (codeSuppr.equals(menuTable[i].getCode())) {
				removed = true;
				continue;
			}
			newMenu[removed ? i - 1 : i] = menuTable[i];
		}

		return newMenu;
	}

}
