package it.univpm.GiAle.twitterProj.filters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import it.univpm.GiAle.twitterProj.model.Tweet;


public class Filter {
	public static ArrayList<Tweet> filterByLikes(ArrayList<Tweet> list, String filter, JsonElement param) {
		// TODO Auto-generated method stub 
		ArrayList<Tweet> listaFiltrata = new ArrayList<Tweet>();
		for (int i = 0; i < list.size(); i++) {
			if (filter.equals("$gt") && list.get(i).getFavorite_count() > param.getAsInt()) {
				listaFiltrata.add(list.get(i));
			}
			if (filter.equals("$lt") && list.get(i).getFavorite_count() < param.getAsInt()) {
				listaFiltrata.add(list.get(i));
			}
			if (filter.equals("$bt")) {
				if (!param.isJsonArray()) {
					// eccezione
					return null;
				}
				else {
					if (list.get(i).getFavorite_count() >= param.getAsJsonArray().get(0).getAsInt() && list.get(i).getFavorite_count() <= param.getAsJsonArray().get(1).getAsInt())
					listaFiltrata.add(list.get(i));
				}
				
			}
		}
		return listaFiltrata;
	}

	public static ArrayList<Tweet> filterByRetweet(ArrayList<Tweet> list, String filter, JsonElement param) {
		// TODO Auto-generated method stub
		ArrayList<Tweet> listaFiltrata = new ArrayList<Tweet>();
		for (int i = 0; i < list.size(); i++) {
			if (filter.equals("$gt") && list.get(i).getRetweet_count() > param.getAsInt()) {
				listaFiltrata.add(list.get(i));
			}
			if (filter.equals("$lt") && list.get(i).getRetweet_count() < param.getAsInt()) {
				listaFiltrata.add(list.get(i));
			}
			if (filter.equals("$bt")) {
				if (!param.isJsonArray()) {
					// eccezione
					return null;
				}
				else {
					if (list.get(i).getFavorite_count() >= param.getAsJsonArray().get(0).getAsInt() && list.get(i).getFavorite_count() <= param.getAsJsonArray().get(1).getAsInt())
					listaFiltrata.add(list.get(i));
				}
				
			}
		}

		return listaFiltrata;
	}
	
	public static ArrayList<Tweet> filterByTime(ArrayList<Tweet> list, String filter, JsonElement param) throws ParseException {
		// TODO Auto-generated method stub 
		ArrayList<Tweet> listaFiltrata = new ArrayList<Tweet>();
		SimpleDateFormat sf = new SimpleDateFormat("MMM dd yyyy", Locale.ENGLISH);
		sf.setLenient(false);
		Date dataFiltro = sf.parse(param.getAsString());
		
		for (int i = 0; i < list.size(); i++) {
			if (filter.equals("$gt")) {
				if (list.get(i).parsedDate().compareTo(dataFiltro)>0)
				listaFiltrata.add(list.get(i));
			}
			if (filter.equals("$lt")) {
				if (list.get(i).parsedDate().compareTo(dataFiltro)<0)
					listaFiltrata.add(list.get(i));
			}
//			if (filter.equals("$bt")) {
//				if (!param.isJsonArray()) {
//					// eccezione
//					return null;
//				}
//				else {
//					if (list.get(i).parsedDate().compareTo(dataFiltro)>0 && list.get(i).getFavorite_count() <= param.getAsJsonArray().get(1).getAsInt())
//					listaFiltrata.add(list.get(i));
//				}
//				
//			}
		}
		return listaFiltrata;
	}
}
