package fr.pizzeria.utils;

import static fr.pizzeria.console.PizzeriaAdminConsoleApp.LOG;
import java.lang.reflect.Field;

import fr.pizzeria.model.Pizza;

/**
 * @author hmerciol
 *
 */
public abstract class PizzaValidator {

	/**
	 * Validation d'un pizza
	 * 
	 * @param pizza
	 *            Pizza à valider
	 * @return True si pizza valide
	 */
	public static boolean pizzaValide(Pizza pizza) {
		boolean valide = true;
		try {
			Field[] fields = pizza.getClass().getDeclaredFields(); // récupération des attributs
			for (Field f : fields) {
				f.setAccessible(true);
				if (f.isAnnotationPresent(Rule.class)) { // sélection des attributs annotés
					Rule annotation = f.getAnnotation(Rule.class);
					Object value = f.get(pizza);
					if (value.toString().length() > annotation.size()) {
						valide = false;
					}
					if (annotation.uppercase()) { // l'attribut a besoin d'être en majuscules
						String toCheck = value.toString();
						String upperCheck = toCheck.toUpperCase();
						if (!toCheck.equals(upperCheck)) {
							valide = false;
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.info("Problème lors de la vérification de la pizza");
		}
		return valide;
	}
}
