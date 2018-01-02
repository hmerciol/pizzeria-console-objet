package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.DeletePizzaException;

/**
 * @author hmerciol
 *
 */
public class SupprimerPizzaOptionMenu extends OptionMenu {

	public SupprimerPizzaOptionMenu(Scanner scan, IPizzaDao dao) {
		super(scan, dao);
		libelle="Supprimer une pizza";
	}

	@Override
	public void execute() throws DeletePizzaException {
		LOG.info("Suppression d\'une pizza");
		LOG.info("Veuillez choisir la pizza à supprimer.");
		LOG.info("(99 pour abandonner).");

		String codeSuppr = scan.next();

		if (codeSuppr.equals("99"))
			return;

		dao.deletePizza(codeSuppr);
	}

}
