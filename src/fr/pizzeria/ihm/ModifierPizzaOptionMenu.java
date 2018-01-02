package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * @author hmerciol
 *
 */
public class ModifierPizzaOptionMenu extends OptionMenu {

	public ModifierPizzaOptionMenu(Scanner scan, IPizzaDao dao) {
		super(scan, dao);
		libelle = "Mettre à jour une pizza";
	}

	@Override
	public void execute() throws UpdatePizzaException {
		LOG.info("Mise à jour d\'une pizza");
		LOG.info("Veuillez choisir la pizza à modifier.");
		LOG.info("(99 pour abandonner).");

		String codeOld = scan.next();

		if (codeOld.equals("99"))
			return;

		LOG.info("Veuillez saisir le code");
		String code = scan.next();
		LOG.info("Veuillez saisir le nom (sans espace)");
		String nom = scan.next();
		LOG.info("Veuillez saisir la catégorie (avec \'_\' pour les espaces)");
		String categorie = scan.next().toUpperCase();
		LOG.info("Veuillez saisir le prix");
		double prix = Double.parseDouble(scan.next());

		dao.updatePizza(codeOld, new Pizza(code, nom, prix, CategoriePizza.valueOf(categorie)));
	}

}
