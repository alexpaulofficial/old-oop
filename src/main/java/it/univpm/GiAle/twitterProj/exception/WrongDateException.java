package it.univpm.GiAle.twitterProj.exception;

/**
 * Eccezione nel caso in cui venga inserita una data sbagliata
 * @author Verdolini Gian Paolo, Paolucci Alessio
 *
 */

public class WrongDateException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public WrongDataException (String message) {
		super(message);
	}
}
