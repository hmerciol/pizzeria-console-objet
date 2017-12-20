package fr.pizzeria.model;

import java.text.DecimalFormat;

/**
 * @author hmerciol
 *
 */
public class Pizza {

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
	private String code;

	/**
	 * Nom complet de la pizza
	 */
	private String nom;

	/**
	 * Prix de la pizza
	 */
	private double prix;

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
		DecimalFormat formatter = new DecimalFormat("#.00");
		return code + " -> " + nom + " (" + formatter.format(prix) + " €), pizza " + categorie.getValue();
	}

}
