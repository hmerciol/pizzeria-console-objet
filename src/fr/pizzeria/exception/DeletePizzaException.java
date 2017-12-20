package fr.pizzeria.exception;

/**
 * @author hmerciol
 *
 */
public class DeletePizzaException extends StockageException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DeletePizzaException() {
		super();
	}

	public DeletePizzaException(String message) {
		super(message);
	}

}
