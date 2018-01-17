package fr.pizzeria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import fr.pizzeria.utils.PizzaToString;
import fr.pizzeria.utils.Rule;
import fr.pizzeria.utils.ToString;

/**
 * @author hmerciol
 *
 */
@Entity
@Table(name="pizza")
public class Pizza implements Comparable<Pizza> {

	/**
	 * Compteur servant à donner un identifiant unique à chaque pizza
	 */
	@Transient
	private static int compteur = 0;

	/**
	 * Identifiant unique de la pizza
	 */
	@Id
	@Column(name="pizza_id")
	private Integer id;

	/**
	 * Code d'identification de la pizza
	 */
	@ToString(after = "->")
	@Rule(size = 3, uppercase = true)
	@Column(name="pizza_code")
	private String code;

	/**
	 * Nom complet de la pizza
	 */
	@ToString
	@Column(name="pizza_nom")
	private String nom;

	/**
	 * Prix de la pizza
	 */
	@ToString(before = " (", after = " €)", format = true)
	@Column(name="pizza_prix")
	private double prix;

	/**
	 * Catégorie de la pizza
	 */
	@ToString(categorie = true, before = " régime ")
	@Enumerated(EnumType.STRING)
	@Column(name="pizza_categorie")
	private CategoriePizza categorie;

	/**
	 * Constructeur par défaut pour hibernate
	 */
	public Pizza() {
		super();
	}

	/**
	 * Constructeur complet
	 * 
	 * @param code
	 *            Le code la pizza
	 * @param nom
	 *            Le nom de la pizza
	 * @param prix
	 *            Le prix de la pizza
	 * @param categorie
	 *            La catégorie de la pizza
	 */
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

	/**
	 * pour hibernate
	 * 
	 * @param id
	 *            l'identifiant
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * pour hibernate
	 * 
	 * @param code
	 *            le code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * pour hibernate
	 * 
	 * @param nom
	 *            le nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * pour hibernate
	 * 
	 * @param prix
	 *            le prix
	 */
	public void setPrix(double prix) {
		this.prix = prix;
	}

	/**
	 * pour hibernate
	 * 
	 * @param categorie
	 *            la catégorie
	 */
	public void setCategorie(CategoriePizza categorie) {
		this.categorie = categorie;
	}

	@Override
	public String toString() {
		return PizzaToString.getString(this);
	}

	@Override
	public int compareTo(Pizza pizza) {
		return code.compareTo(pizza.getCode());
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Pizza) {
			Pizza piz = (Pizza) obj;
			return code.equals(piz.getCode());
		}
		return super.equals(obj);
	}

}
