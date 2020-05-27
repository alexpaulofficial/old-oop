package it.univpm.GiAle.twitterProj.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import it.univpm.GiAle.twitterProj.model.Tweet;

public interface TweetService {
	public abstract ArrayList<Tweet> getTweet(); 
	public abstract void addTweetsArray (Tweet[] tweetArray);
	public abstract Tweet[] addJson (String body);
}
