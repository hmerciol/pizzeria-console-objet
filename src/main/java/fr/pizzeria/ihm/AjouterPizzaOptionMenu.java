package fr.pizzeria.ihm;

import static fr.pizzeria.console.PizzeriaAdminConsoleApp.LOG;
import static fr.pizzeria.console.PizzeriaAdminConsoleApp.TRACE;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * @author hmerciol
 *
 */
public class AjouterPizzaOptionMenu extends OptionMenu {

	public AjouterPizzaOptionMenu(Scanner scan, IPizzaDao dao) {
		super(scan, dao);
		libelle = "Ajouter une nouvelle pizza";
	}

	@Override
	public void execute() throws StockageException {
		LOG.info("Ajout d\'une nouvelle pizza");
		LOG.info("Veuillez saisir le code");
		String code = scan.next();
		TRACE.debug("Commande utilisateur : " + code);
		LOG.info("Veuillez saisir le nom (sans espace)");
		String nom = scan.next();
		TRACE.debug("Commande utilisateur : " + nom);
		LOG.info("Veuillez saisir la catégorie (avec \'_\' pour les espaces)");
		String categorie = scan.next();
		TRACE.debug("Commande utilisateur : " + categorie);
		LOG.info("Veuillez saisir le prix");
		double prix = Double.parseDouble(scan.next());
		TRACE.debug("Commande utilisateur : " + prix);

		dao.saveNewPizza(new Pizza(code, nom, prix, CategoriePizza.valueOf(categorie.toUpperCase())));
	}

}
