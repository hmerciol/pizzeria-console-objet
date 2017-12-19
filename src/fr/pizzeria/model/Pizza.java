package fr.pizzeria.model;

public class Pizza {

	public static Integer lastId;
	private Integer id;
	private String code;
	private String nom;
	private double prix;
	
	public Pizza(String code, String nom, double prix) {
		super();
		this.id = ++lastId;
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

	public void setCode(String code) {
		this.code = code;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	
}
