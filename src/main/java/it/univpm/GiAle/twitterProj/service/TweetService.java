package it.univpm.GiAle.twitterProj.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import it.univpm.GiAle.twitterProj.exception.WrongFilterException;
import it.univpm.GiAle.twitterProj.exception.getTweetException;
import it.univpm.GiAle.twitterProj.model.Tweet;

public interface TweetService {
	public abstract ArrayList<Tweet> getTweet() throws getTweetException; 
	public abstract void addTweetsArray (Tweet[] tweetArray);
	public abstract Tweet[] addJson (String body);
	
	public abstract ArrayList<Tweet> filtraggio (String body, ArrayList<Tweet> list) throws WrongFilterException;
	public abstract String GetFromTwitter (String url) throws MalformedURLException, IOException;
}
