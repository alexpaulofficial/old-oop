package it.univpm.GiAle.twitterProj.exception;

/**
 * Eccezione nel caso in cui venga restituita una lista vuota
 * @author Verdolini Gian Paolo, Paolucci Alessio
 *
 */

public class getTweetException extends Exception {
	
private static final long serialVersionUID = 1L;

	/**
	 * Viene stampato il messaggio d' errore
	 * @return String
	 */

		
		public getTweetException(String message) {
		super(message);
	
	}

}
