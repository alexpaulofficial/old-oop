package it.univpm.GiAle.twitterProj.tests;

/**
 * Questo test serve per verificare che parta l' eccezione nel caso in cui venga inserito
 * un nome sbagliato nel caso del filtraggio
 * @author Verdolini Gian Paolo, Paolucci Alessio
 */

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.univpm.GiAle.twitterProj.exception.WrongFilterException;
import it.univpm.GiAle.twitterProj.exception.getTweetException;
import it.univpm.GiAle.twitterProj.service.TweetService;
import it.univpm.GiAle.twitterProj.service.TweetServiceImpl;
import it.univpm.GiAle.twitterProj.model.Tweet;
import java.util.ArrayList;

class testProva {
	TweetServiceImpl prova;

	@BeforeEach
	void setUp() throws Exception {
		}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void test2() throws WrongFilterException, getTweetException, MalformedURLException, IOException{
		prova = new TweetServiceImpl();
		
		prova.addJson(prova.GetFromTwitter("https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/api/1.1/search/tweets.json?from=UnivPoliMarche&count=100"));
	/**
	 *  Si crea una lista fittizia perchè altrimenti partirebbe l' errore 
	 */
		String body = "{\r\n" + 
				"	\"filter_field\":\"likes\",\r\n" + 
				"	\"filter_type\":\"$bt\",\r\n" + 
				"	\"parameters\": [10]\r\n" + 
				"}";
		ArrayList<Tweet> list = new ArrayList<Tweet>();
		Tweet tweet1 = new Tweet("Wed Jun 17 13:24:08 +0000 2020", 12766376, "ciao", 3, 2);
		Tweet tweet2 = new Tweet("Wed Jun 19 13:24:08 +0000 2020", 127676, "bella", 6, 7);
		list.add(tweet1);
		list.add(tweet2);
		
		assertThrows(WrongFilterException.class, ()-> prova.filtraggio(body, list));
		//assertEquals("Il filtro inserito non è corretto", ciao.filtraggio(body, ciao.getTweet()));
		
	}

}
