package fr.pizzeria.exception;

/**
 * @author hmerciol
 *
 */
public class UpdatePizzaException extends StockageException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UpdatePizzaException() {
		super();
	}

	public UpdatePizzaException(String message) {
		super(message);
	}

}
