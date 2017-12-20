package fr.pizzeria.ihm;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;

/**
 * @author hmerciol
 *
 */
public class MenuPizzeria {

	/**
	 * Liste des options du menu console
	 */
	private Map<Integer, OptionMenu> actions;

	/**
	 * Référence vers le scanner de la console
	 */
	private Scanner scan;

	/**
	 * Vaut true tant que l'application tourne
	 */
	private boolean on = false;

	public MenuPizzeria(Scanner scan, IPizzaDao dao) {
		super();
		this.scan = scan;
		actions = new LinkedHashMap<>(4);
		actions.put(1, new ListerPizzasOptionMenu(scan, dao));
		actions.put(2, new AjouterPizzaOptionMenu(scan, dao));
		actions.put(3, new ModifierPizzaOptionMenu(scan, dao));
		actions.put(4, new SupprimerPizzaOptionMenu(scan, dao));
	}

	/**
	 * Affiche en console les options en lignes de commande
	 */
	public void afficher() {
		System.out.println("***** Pizzeria Administration *****");
		Set<Integer> keys = actions.keySet();
		for (Integer key : keys) {
			System.out.println(key.intValue() + ". " + actions.get(key).getLibelle());
		}
		System.out.println("99. Sortir");
	}

	/**
	 * Démarrage de l'affichage console et traitement des instructions utilisateur
	 */
	public void demarreConsole() {
		on = true;
		int instruction;
		while (on) {
			afficher();
			instruction = scan.nextInt();
			try {
				executeMenu(instruction);
			} catch (StockageException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * Exécute une option du menu en fonction de son identifiant
	 * 
	 * @param indice
	 *            Numéro de l'option du menu
	 * @throws StockageException
	 *             Envoyé en cas de mauvaise manipulation du menu des pizzas
	 */
	public void executeMenu(int indice) throws StockageException {
		if (indice == 99) {
			System.out.println("Au revoir :(");
			on = false;
		} else {
			actions.get(indice).execute();
		}
	}
}
