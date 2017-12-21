package fr.pizzeria.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author hmerciol
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Rule {

	/**
	 * Taille maximale d'un élément
	 * 
	 * @return Taille maximale
	 */
	int size() default Integer.MAX_VALUE;

	/**
	 * Indicateur pour savoir s'il faut que l'élément soit en majuscule
	 * 
	 * @return True si la casse doit être en majuscule
	 */
	boolean uppercase() default false;
}
