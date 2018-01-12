package fr.pizzeria.console;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.ihm.MenuPizzeria;

/**
 * @author hmerciol
 *
 */
public class PizzeriaAdminConsoleApp {

	/**
	 * Logger de sortie console de l'application
	 */
	public static final Logger LOG = LoggerFactory.getLogger("console");

	/**
	 * Logger de trace de l'application
	 */
	public static final Logger TRACE = LoggerFactory.getLogger("trace");

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		IPizzaDao menuPizzeria = IPizzaDao.getPizzaDao();

		MenuPizzeria console = new MenuPizzeria(scan, menuPizzeria);
		console.demarreConsole();

		scan.close();
	}

}
