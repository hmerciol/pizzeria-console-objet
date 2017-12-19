package fr.pizzeria.model;

/**
 * @author hmerciol
 *
 */
public class Pizza {

	private static int compteur=0;
	private Integer id;
	private String code;
	private String nom;
	private double prix;
	
	public Pizza(String code, String nom, double prix) {
		super();
		this.id = compteur++;
		this.code = code;
		this.nom = nom;
		this.prix = prix;
	}

	public Integer getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getNom() {
		return nom;
	}

	public double getPrix() {
		return prix;
	}
	
	
}
