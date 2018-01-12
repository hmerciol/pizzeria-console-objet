package fr.pizzeria.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import fr.pizzeria.exception.StockageException;
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

	/**
	 * Remplit la liste de pizzas locale à partir du fichier
	 * @throws StockageException En cas de problème lors de l'accès au fichier
	 */
	private void ouvrirFichier() throws StockageException {
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
			throw new StockageException("Problème d'accès lors de la lecture du fichier");
		}
	}

	/**
	 * Ecrit la liste de pizzas dans le fichier puis vide la liste locale
	 * @throws StockageException En cas de problème lors de l'accès au fichier
	 */
	private void fermerFichier() throws StockageException {
		BufferedWriter output = null;
		try {
			output = Files.newBufferedWriter(Paths.get(URL_FICHIER));
			for (Pizza pizza : menuPizzas) {
				output.write(PizzaToString.getData(pizza) + "\n");
			}
			output.close();
			menuPizzas = null;
		} catch (IOException e) {
			throw new StockageException("Problème d'accès lors de l\'écriture du fichier");
		}
	}

	@Override
	public List<Pizza> findAllPizzas() throws StockageException {
		ouvrirFichier();
		List<Pizza> retour = super.findAllPizzas();
		fermerFichier();
		return retour;
	}

	@Override
	public void saveNewPizza(Pizza pizza) throws StockageException {
		ouvrirFichier();
		super.saveNewPizza(pizza);
		fermerFichier();
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) throws StockageException {
		ouvrirFichier();
		super.updatePizza(codePizza, pizza);
		fermerFichier();
	}

	@Override
	public void deletePizza(String codePizza) throws StockageException {
		ouvrirFichier();
		super.deletePizza(codePizza);
		fermerFichier();
	}

}
