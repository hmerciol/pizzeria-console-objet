package fr.pizzeria.model;

import java.lang.reflect.Field;
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
	@ToString(separateur = "->")
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
		DecimalFormat formatter = new DecimalFormat("#.00");
		StringBuilder chaine = new StringBuilder();
		try {
			Field[] fields = this.getClass().getDeclaredFields(); // récupération des attributs
			for (Field f : fields) {
				f.setAccessible(true);
				if (f.isAnnotationPresent(ToString.class)) { // sélection des attributs annotés
					ToString annotation = f.getAnnotation(ToString.class);
					Object value = f.get(this);
					if (annotation.format()) { // l'attribut prix a besoin d'un formatage
						chaine.append(formatter.format(value).toString());
					} else if (annotation.categorie()) { // l'attribut catégorie a besoin d'appeler getValue()
						chaine.append(((CategoriePizza) value).getValue());
					} else {
						if (annotation.uppercase()) {
							chaine.append(value.toString().toUpperCase());
						} else {
							chaine.append(value.toString());
						}
					}
					chaine.append(annotation.separateur());
				}
			}
		} catch (Exception e) {
			System.out.println("Problème lors de l'affichage du menu");
		}
		return chaine.toString();
	}

}
