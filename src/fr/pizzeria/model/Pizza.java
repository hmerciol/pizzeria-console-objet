package fr.pizzeria.model;

public class Pizza {

	private Integer id;
	private String code;
	private String nom;
	private double prix;
	
	public Pizza(Integer id, String code, String nom, double prix) {
		super();
		this.id = id;
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
