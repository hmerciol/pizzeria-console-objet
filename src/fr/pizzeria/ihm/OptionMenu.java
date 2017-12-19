package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.model.Pizza;

/**
 * @author hmerciol
 *
 */
public abstract class OptionMenu {

	/**
	 * Pour obtenir le libellé d'une option dans le menu
	 * @return libelle
	 */
	public abstract String getLibelle();
	
	/**
	 * Pour exécuter la commande corespondante
	 * @param menuTable
	 * @param scan
	 * @return
	 */
	public abstract Pizza[] execute(Pizza[] menuTable, Scanner scan);
}
