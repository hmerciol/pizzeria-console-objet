package fr.pizzeria.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author hmerciol
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ToString {

	/**
	 * Séparateur entre les éléments à afficher
	 * 
	 * @return Séparateur
	 */
	String separateur() default "";

	/**
	 * Indicateur pour savoir s'il faut forcer la mise en majuscule de l'élément à
	 * afficher
	 * 
	 * @return True pour forcer la casse en majuscule
	 */
	boolean uppercase() default false;

	/**
	 * Si l'élément annoté a besoin d'un formatage particulier
	 * 
	 * @return True si un formatage est nécessaire
	 */
	boolean format() default false;

	/**
	 * Si l'élément annoté est un CategoriePizza et a besoin d'appeler une méthode
	 * getValue() au lieu de toString()
	 * 
	 * @return True si c'est une catégorie
	 */
	boolean categorie() default false;
}
