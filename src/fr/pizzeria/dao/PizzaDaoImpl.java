package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;

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
	List<Pizza> menuPizzas = new ArrayList<Pizza>();

	public PizzaDaoImpl() {
		super();
	}

	public PizzaDaoImpl(List<Pizza> menuPizzas) {
		super();
		this.menuPizzas = menuPizzas;
	}

	@Override
	public List<Pizza> findAllPizzas() {
		return menuPizzas;
	}

	@Override
	public void saveNewPizza(Pizza pizza) throws SavePizzaException {
		for (Pizza courant : menuPizzas) {
			if (courant.getCode().equals(pizza.getCode())) {
				throw new SavePizzaException("Code déjà utilisé, pizza non ajoutée");
			}
		}
		menuPizzas.add(pizza);
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException {
		boolean updated = false;
		Pizza toDelete = null;
		int at = -1;
		for (Pizza courant : menuPizzas) {
			if (courant.getCode().equals(codePizza)) {
				toDelete = courant;
				at = menuPizzas.indexOf(courant);
				updated = true;
				break;
			} else if (courant.getCode().equals(pizza.getCode())) {
				throw new UpdatePizzaException("Code pizza déjà utilisé ailleurs");
			}
		}
		if (!updated) {
			throw new UpdatePizzaException("Code pizza non trouvé");
		}
		menuPizzas.remove(toDelete);
		menuPizzas.add(at, pizza);
	}

	@Override
	public void deletePizza(String codePizza) throws DeletePizzaException {
		boolean removed = false;
		for (Pizza courant : menuPizzas) {
			if (codePizza.equals(courant.getCode())) {
				menuPizzas.remove(courant);
				removed = true;
				break;
			}
		}
		if (!removed)
			throw new DeletePizzaException("Pizza non trouvée, aucune suppression effectuée");
	}

}
