package fr.pizzeria.dao;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

/**
 * @author hmerciol
 *
 */
public class PizzaDaoImpl implements IPizzaDao {

	/**
	 * Liste des pizzas du menu
	 */
	Pizza[] menuPizzas = new Pizza[0];

	public PizzaDaoImpl() {
		super();
	}

	public PizzaDaoImpl(Pizza[] menuPizzas) {
		super();
		this.menuPizzas = menuPizzas;
	}

	@Override
	public Pizza[] findAllPizzas() {
		return menuPizzas;
	}

	@Override
	public void saveNewPizza(Pizza pizza) throws SavePizzaException {
		Pizza[] newMenu = new Pizza[menuPizzas.length + 1];
		for (int i = 0; i < menuPizzas.length; i++) {
			if (menuPizzas[i].getCode().equals(pizza.getCode()))
				throw new SavePizzaException("Code d�j� utilis�, pizza non ajout�e");
			newMenu[i] = menuPizzas[i];
		}
		newMenu[menuPizzas.length] = pizza;
		menuPizzas = newMenu;
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException {
		boolean updated = false;
		for (int i = 0; i < menuPizzas.length; i++) {
			if (menuPizzas[i].getCode().equals(codePizza)) {
				menuPizzas[i] = pizza;
				updated = true;
			} else if (menuPizzas[i].getCode().equals(pizza.getCode())) {
				throw new UpdatePizzaException("Code pizza d�j� utilis� ailleurs");
			}
		}
		if (!updated)
			throw new UpdatePizzaException("Code pizza non trouv�");
	}

	@Override
	public void deletePizza(String codePizza) throws DeletePizzaException {

		Pizza[] newMenu = new Pizza[menuPizzas.length - 1];
		boolean removed = false;

		for (int i = 0; i < menuPizzas.length; i++) {
			if (codePizza.equals(menuPizzas[i].getCode())) {
				removed = true;
				continue;
			}

			if (!removed && i == newMenu.length)
				throw new DeletePizzaException("Pizza non trouv�e, aucune suppression effectu�e");

			newMenu[removed ? i - 1 : i] = menuPizzas[i];
		}

		menuPizzas = newMenu;
	}

}
