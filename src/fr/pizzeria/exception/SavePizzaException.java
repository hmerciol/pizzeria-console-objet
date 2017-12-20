package fr.pizzeria.exception;

/**
 * @author hmerciol
 *
 */
public class SavePizzaException extends StockageException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SavePizzaException() {
		super();
	}

	public SavePizzaException(String message) {
		super(message);
	}

}
