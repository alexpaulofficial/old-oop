package it.univpm.GiAle.twitterProj.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Questo test verifica che ritorni una lista vuota
 * @author Verdolini Gian Paolo, Paolucci Alessio
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.univpm.GiAle.twitterProj.exception.WrongFilterException;
import it.univpm.GiAle.twitterProj.exception.getTweetException;
import it.univpm.GiAle.twitterProj.service.TweetService;
import it.univpm.GiAle.twitterProj.service.TweetServiceImpl;

class TestProj {
	TweetServiceImpl tsi;

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void test1() throws getTweetException, WrongFilterException {
		tsi = new TweetServiceImpl();
		assertThrows(getTweetException.class, ()-> tsi.getTweet());
		
		
	

}
}
