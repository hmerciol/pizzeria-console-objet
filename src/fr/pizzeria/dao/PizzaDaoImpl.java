package fr.pizzeria.dao;

import fr.pizzeria.model.Pizza;

public class PizzaDaoImpl implements IPizzaDao {
	
	Pizza[] menuPizzas = new Pizza[0];

	@Override
	public Pizza[] findAllPizzas() {
		return menuPizzas;
	}

	@Override
	public boolean saveNewPizza(Pizza pizza) {
		Pizza[] newMenu = new Pizza[menuPizzas.length + 1];
		for (int i = 0; i < menuPizzas.length; i++) {
			newMenu[i] = menuPizzas[i];
		}
		newMenu[menuPizzas.length] = pizza;
		menuPizzas = newMenu;
		return true;
	}

	@Override
	public boolean updatePizza(String codePizza, Pizza pizza) {
		for (int i = 0; i < menuPizzas.length; i++) {
			if (menuPizzas[i].getCode().equals(codePizza)) {
				menuPizzas[i]=pizza;
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean deletePizza(String codePizza) {

		Pizza[] newMenu = new Pizza[menuPizzas.length - 1];
		boolean removed = false;

		for (int i = 0; i < menuPizzas.length; i++) {
			if (codePizza.equals(menuPizzas[i].getCode())) {
				removed = true;
				continue;
			}
			newMenu[removed ? i - 1 : i] = menuPizzas[i];
		}
		
		menuPizzas=newMenu;
		return removed;
	}

}
