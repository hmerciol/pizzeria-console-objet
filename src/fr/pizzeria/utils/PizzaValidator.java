package fr.pizzeria.utils;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.model.Pizza;

/**
 * @author hmerciol
 *
 */
public abstract class PizzaValidator {

	/**
	 * Logger du validateur
	 */
	private static final Logger LOG = LoggerFactory.getLogger("console");

	/**
	 * Validation d'un pizza
	 * 
	 * @param pizza
	 *            Pizza � valider
	 * @return True si pizza valide
	 */
	public static boolean pizzaValide(Pizza pizza) {
		boolean valide = true;
		try {
			Field[] fields = pizza.getClass().getDeclaredFields(); // r�cup�ration des attributs
			for (Field f : fields) {
				f.setAccessible(true);
				if (f.isAnnotationPresent(Rule.class)) { // s�lection des attributs annot�s
					Rule annotation = f.getAnnotation(Rule.class);
					Object value = f.get(pizza);
					if (value.toString().length() > annotation.size()) {
						valide = false;
					}
					if (annotation.uppercase()) { // l'attribut a besoin d'�tre en majuscules
						String toCheck = value.toString();
						String upperCheck = toCheck.toUpperCase();
						if (!toCheck.equals(upperCheck)) {
							valide = false;
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.info("Probl�me lors de la v�rification de la pizza");
		}
		return valide;
	}
}
