package it.univpm.GiAle.twitterProj.filters;

import java.util.ArrayList;
import java.util.HashMap;


import it.univpm.GiAle.twitterProj.filters.Filter;
import it.univpm.GiAle.twitterProj.model.Tweet;

public class Stats extends Filter {
	public static HashMap<String, Integer> stats(ArrayList<Tweet> list) {
		int media_favorite = 0;
		int min_favorite = list.get(0).getFavorite_count();
		int max_favorite = -1;
		int media_retweet = 0;
		int min_retweet = list.get(0).getRetweet_count();
		int max_retweet = -1;
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getFavorite_count() > max_favorite) {
				max_favorite = list.get(i).getFavorite_count();
			}
			if (list.get(i).getFavorite_count() < min_favorite) {
				min_favorite = list.get(i).getFavorite_count();
			}
			if (list.get(i).getRetweet_count() > max_retweet) {
				max_retweet= list.get(i).getRetweet_count();
			}
			if (list.get(i).getRetweet_count() < min_retweet) {
				min_retweet = list.get(i).getRetweet_count();
			}
			media_favorite += list.get(i).getFavorite_count();
			media_retweet += list.get(i).getRetweet_count();
		}
		media_favorite = media_favorite / list.size();
		media_retweet = media_retweet / list.size();
		HashMap<String, Integer> statMap = new HashMap<String, Integer>();
		statMap.put("media_favorite", media_favorite);
		statMap.put("min_favorite", min_favorite);
		statMap.put("max_favorite", max_favorite);
		statMap.put("media_retweet", media_retweet);
		statMap.put("min_retweet", min_retweet);
		statMap.put("max_retweet", max_retweet);
		return statMap;		
		
	}
}
