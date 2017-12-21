package fr.pizzeria.model;

import fr.pizzeria.utils.PizzaToString;
import fr.pizzeria.utils.Rule;
import fr.pizzeria.utils.ToString;

/**
 * @author hmerciol
 *
 */
public class Pizza implements Comparable<Pizza> {

	/**
	 * Compteur servant à donner un identifiant unique à chaque pizza
	 */
	private static int compteur = 0;

	/**
	 * Identifiant unique de la pizza
	 */
	private Integer id;

	/**
	 * Code d'identification de la pizza
	 */
	@ToString(separateur = "->")
	@Rule(size = 3, uppercase = true)
	private String code;

	/**
	 * Nom complet de la pizza
	 */
	@ToString(separateur = " (")
	private String nom;

	/**
	 * Prix de la pizza
	 */
	@ToString(separateur = " €) régime ", format = true)
	private double prix;

	/**
	 * Catégorie de la pizza
	 */
	@ToString(categorie = true)
	private CategoriePizza categorie;

	public Pizza(String code, String nom, double prix, CategoriePizza categorie) {
		super();
		this.id = compteur++;
		this.code = code;
		this.nom = nom;
		this.prix = prix;
		this.categorie = categorie;
	}

	/**
	 * Renvoie l'identifiant unique de la pizza
	 * 
	 * @return L'identifiant unique de la pizza
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Renvoie le code de la pizza
	 * 
	 * @return Le code de la pizza
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Renvoie le nom complet de la pizza
	 * 
	 * @return Le nom complet de la pizza
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Renvoie le prix de la pizza
	 * 
	 * @return Le prix de la pizza
	 */
	public double getPrix() {
		return prix;
	}

	/**
	 * Renvoie la catégorie de la pizza
	 * 
	 * @return La catégorie de la pizza
	 */
	public CategoriePizza getCategorie() {
		return categorie;
	}

	@Override
	public String toString() {
		return PizzaToString.getString(this);
	}

	@Override
	public int compareTo(Pizza pizza) {
		return code.compareTo(pizza.getCode());
	}

}
