package it.univpm.GiAle.twitterProj.filters;

import java.util.ArrayList;


import it.univpm.GiAle.twitterProj.model.Tweet;


public class Filter {
	public static ArrayList<Tweet> filterByLikes(ArrayList<Tweet> list, String filter, int likes) {
		// TODO Auto-generated method stub 
		ArrayList<Tweet> listaFiltrata = new ArrayList<Tweet>();
		for (int i = 0; i < list.size(); i++) {
			if (filter.equals("$gt") && list.get(i).getFavorite_count() > likes) {
				listaFiltrata.add(list.get(i));
			}
			if (filter.equals("$lt") && list.get(i).getFavorite_count() < likes) {
				listaFiltrata.add(list.get(i));
			}
		}
		return listaFiltrata;
	}

	public static ArrayList<Tweet> filterByRetweet(ArrayList<Tweet> list, String filter, int retweet) {
		// TODO Auto-generated method stub
		ArrayList<Tweet> listaFiltrata = new ArrayList<Tweet>();
		for (int i = 0; i < list.size(); i++) {
			if (filter.equals("$gt") && list.get(i).getRetweet_count() > retweet) {
				listaFiltrata.add(list.get(i));
			}
			if (filter.equals("$lt") && list.get(i).getRetweet_count() < retweet) {
				listaFiltrata.add(list.get(i));
			}
		}
		return listaFiltrata;
	}
}
