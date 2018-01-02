package fr.pizzeria.utils;

import java.lang.reflect.Field;
import java.text.DecimalFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * @author hmerciol
 *
 */
public abstract class PizzaToString {

	/**
	 * Logger de PizzaToString
	 */
	private static final Logger LOG = LoggerFactory.getLogger("console");

	/**
	 * S�parateur pour le stockage dans le fichier
	 */
	private static String separator = " & ";

	/**
	 * Renvoie un String avec le bon formatage pour l'affichage d'une pizza dans un
	 * menu
	 * 
	 * @param pizza
	 *            La pizza � imprimer
	 * @return Le String bien formatt�
	 */
	public static String getString(Pizza pizza) {
		DecimalFormat formatter = new DecimalFormat("#.00");
		StringBuilder chaine = new StringBuilder();
		try {
			Field[] fields = pizza.getClass().getDeclaredFields(); // r�cup�ration des attributs
			for (Field f : fields) {
				f.setAccessible(true);
				if (f.isAnnotationPresent(ToString.class)) { // s�lection des attributs annot�s
					ToString annotation = f.getAnnotation(ToString.class);
					Object value = f.get(pizza);
					chaine.append(annotation.before());
					if (annotation.format()) { // l'attribut prix a besoin d'un formatage
						chaine.append(formatter.format(value).toString());
					} else if (annotation.categorie()) { // l'attribut cat�gorie a besoin d'appeler getValue()
						chaine.append(((CategoriePizza) value).getValue());
					} else {
						if (annotation.uppercase()) {
							chaine.append(value.toString().toUpperCase());
						} else {
							chaine.append(value.toString());
						}
					}
					chaine.append(annotation.after());
				}
			}
		} catch (Exception e) {
			LOG.info("Probl�me lors de l'affichage du menu");
		}
		return chaine.toString();
	}

	/**
	 * Renvoie les donn�es de la pizza format�es pour le fichier
	 * 
	 * @param pizza
	 *            La pizza � r�cup�rer
	 * @return Un String contenant les donn�es format�es
	 */
	public static String getData(Pizza pizza) {
		return pizza.getCode() + separator + pizza.getNom() + separator + pizza.getPrix() + separator
				+ pizza.getCategorie().toString();
	}

	/**
	 * Renvoie le s�parateur
	 * 
	 * @return Le s�parateur
	 */
	public static String getSeparator() {
		return separator;
	}
}
