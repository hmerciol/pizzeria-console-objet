package fr.pizzeria.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.utils.PizzaToString;

/**
 * @author hmerciol
 *
 */
public class PizzaDaoFichier extends PizzaDaoImpl {

	/**
	 * URL du fichier de données
	 */
	private final String URL_FICHIER = "pizzas.txt";

	public PizzaDaoFichier() {
		super();
	}

	public PizzaDaoFichier(List<Pizza> menuPizzas) {
		super(menuPizzas);
		fermerFichier();
	}

	/**
	 * Remplit la liste de pizzas locale à partir du fichier
	 */
	private void ouvrirFichier() {
		menuPizzas = new ArrayList<Pizza>();
		BufferedReader input = null;
		String line = null;
		try {
			input = Files.newBufferedReader(Paths.get(URL_FICHIER));
			line = input.readLine();
			while (line != null) { // parse chaque ligne pour créer une Pizza et l'ajouter à la liste
				String[] data = line.split(PizzaToString.getSeparator());
				if (data.length != 4) {
					input.close();
					throw new IOException("ici");
				}
				menuPizzas.add(new Pizza(data[0], data[1], Double.parseDouble(data[2]), CategoriePizza.valueOf(data[3])));
				line = input.readLine();
			}
			input.close();
		} catch (IOException e) {
			System.out.println("Problème d'accès lors de la lecture du fichier");
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Ecrit la liste de pizzas dans le fichier puis vide la liste locale
	 */
	private void fermerFichier() {
		BufferedWriter output = null;
		try {
			output = Files.newBufferedWriter(Paths.get(URL_FICHIER));
			for (Pizza pizza : menuPizzas) {
				output.write(PizzaToString.getData(pizza) + "\n");
			}
			output.close();
			menuPizzas = null;
		} catch (IOException e) {
			System.out.println("Problème d'accès lors de l\'écriture du fichier");
			System.out.println(e.getMessage());
		}
	}

	@Override
	public List<Pizza> findAllPizzas() {
		ouvrirFichier();
		List<Pizza> retour = super.findAllPizzas();
		fermerFichier();
		return retour;
	}

	@Override
	public void saveNewPizza(Pizza pizza) throws SavePizzaException {
		ouvrirFichier();
		super.saveNewPizza(pizza);
		fermerFichier();
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException {
		ouvrirFichier();
		super.updatePizza(codePizza, pizza);
		fermerFichier();
	}

	@Override
	public void deletePizza(String codePizza) throws DeletePizzaException {
		ouvrirFichier();
		super.deletePizza(codePizza);
		fermerFichier();
	}

}
