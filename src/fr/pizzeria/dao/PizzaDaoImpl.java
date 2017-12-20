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
	
	Pizza[] menuPizzas = new Pizza[0];

	@Override
	public Pizza[] findAllPizzas() {
		return menuPizzas;
	}

	@Override
	public boolean saveNewPizza(Pizza pizza) throws SavePizzaException {
		Pizza[] newMenu = new Pizza[menuPizzas.length + 1];
		for (int i = 0; i < menuPizzas.length; i++) {
			if(menuPizzas[i].getCode().equals(pizza.getCode()))
				throw new SavePizzaException("Code déjà utilisé, pizza non ajoutée");
			newMenu[i] = menuPizzas[i];
		}
		newMenu[menuPizzas.length] = pizza;
		menuPizzas = newMenu;
		return true;
	}

	@Override
	public boolean updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException{
		for (int i = 0; i < menuPizzas.length; i++) {
			if (menuPizzas[i].getCode().equals(codePizza)) {
				menuPizzas[i]=pizza;
				return true;
			}
		}
		throw new UpdatePizzaException("Code pizza non trouvé");
	}

	@Override
	public boolean deletePizza(String codePizza) throws DeletePizzaException {

		Pizza[] newMenu = new Pizza[menuPizzas.length - 1];
		boolean removed = false;

		for (int i = 0; i < menuPizzas.length; i++) {
			if (codePizza.equals(menuPizzas[i].getCode())) {
				removed = true;
				continue;
			}
			
			if (!removed && i==newMenu.length)
				throw new DeletePizzaException("Pizza non trouvée, aucune suppression effectuée");
			
			newMenu[removed ? i - 1 : i] = menuPizzas[i];
		}
		
		menuPizzas=newMenu;
		return true;
	}

}
