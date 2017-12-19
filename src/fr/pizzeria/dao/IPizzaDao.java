package fr.pizzeria.dao;

import fr.pizzeria.model.Pizza;

public interface IPizzaDao {
	
	public Pizza[] findAllPizzas();
	public boolean saveNewPizza(Pizza pizza);
	public boolean updatePizza(String codePizza, Pizza pizza);
	public boolean deletePizza(String codePizza);

}
