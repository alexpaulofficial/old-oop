package it.univpm.GiAle.twitterProj.service;

import java.util.ArrayList;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import it.univpm.GiAle.twitterProj.model.Tweet;

@Service
public class TweetServiceImpl implements TweetService {
	private static ArrayList<Tweet> myTweetList = new ArrayList<Tweet>();
	@Override
	public ArrayList<Tweet> getTweet() {
		// TODO Auto-generated method stub
		return myTweetList;
	}

	@Override
	public void addTweetsArray(Tweet[] tweetArray) {
		// TODO Auto-generated method stub
		myTweetList.clear();
		for (int i = 0; i < tweetArray.length; i++)
		{
			myTweetList.add(tweetArray[i]);
		}
	}

	@Override
	public Tweet[] addJson(String body) {
		// TODO Auto-generated method stub
		JsonObject myObject = new Gson().fromJson(body, JsonObject.class);
		JsonArray array = myObject.getAsJsonArray("statuses"); 
		Gson GoogleSon = new Gson();
		Tweet[] gsonArray = GoogleSon.fromJson(array, Tweet[].class);
		return gsonArray;
	}

}
