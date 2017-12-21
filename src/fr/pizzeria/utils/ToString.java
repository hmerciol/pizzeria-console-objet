package fr.pizzeria.utils;

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
	 * S�parateur avant l'�l�ment � afficher
	 * 
	 * @return S�parateur
	 */
	String before() default "";

	/**
	 * S�parateur apr�s l'�l�ment � afficher
	 * 
	 * @return S�parateur
	 */
	String after() default "";

	/**
	 * Indicateur pour savoir s'il faut forcer la mise en majuscule de l'�l�ment �
	 * afficher
	 * 
	 * @return True pour forcer la casse en majuscule
	 */
	boolean uppercase() default false;

	/**
	 * Si l'�l�ment annot� a besoin d'un formatage particulier
	 * 
	 * @return True si un formatage est n�cessaire
	 */
	boolean format() default false;

	/**
	 * Si l'�l�ment annot� est un CategoriePizza et a besoin d'appeler une m�thode
	 * getValue() au lieu de toString()
	 * 
	 * @return True si c'est une cat�gorie
	 */
	boolean categorie() default false;
}
