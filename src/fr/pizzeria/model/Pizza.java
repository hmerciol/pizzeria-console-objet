package fr.pizzeria.model;

/**
 * @author hmerciol
 *
 */
public class Pizza {

	/**
	 * Compteur servant à donner un identifiant unique à chaque pizza
	 */
	private static int compteur=0;
	
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
	
	public Pizza(String code, String nom, double prix) {
		super();
		this.id = compteur++;
		this.code = code;
		this.nom = nom;
		this.prix = prix;
	}

	/**
	 * Renvoie l'identifiant unique de la pizza
	 * @return
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Renvoie le code de la pizza
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Renvoie le nom complet de la pizza
	 * @return
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Renvoie le prix de la pizza
	 * @return
	 */
	public double getPrix() {
		return prix;
	}
	
	
}
