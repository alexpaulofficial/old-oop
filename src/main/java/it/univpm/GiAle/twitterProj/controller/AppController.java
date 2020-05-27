package it.univpm.GiAle.twitterProj.controller;

import java.text.ParseException;

import javax.swing.text.html.parser.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import it.univpm.GiAle.twitterProj.filters.Filter;
import it.univpm.GiAle.twitterProj.model.Tweet;
import it.univpm.GiAle.twitterProj.service.TweetService;

@RestController
public class AppController {
	@Autowired
	TweetService service;
	@RequestMapping (value = "/data", method = RequestMethod.GET)
	public ResponseEntity<Object> getTweet () {
		return new ResponseEntity<> (service.getTweet(), HttpStatus.OK);
	}

	@PostMapping("/data")
	public ResponseEntity<Object> exampleMethod2 (@RequestBody String body) {
		
		service.addTweetsArray(service.addJson(body));
		return new ResponseEntity<> ("Tweet caricati correttamente. Per verificare, fare una richiesta GET allo stesso indirizzo", HttpStatus.OK);
	}
	@GetMapping("/data/filter")
	public ResponseEntity<Object> filter (@RequestBody String bodyFilter) {
		JsonObject gson = new Gson().fromJson(bodyFilter, JsonObject.class);
		String filterFiled = gson.get("filter_field").getAsString();
		String filterType = gson.get("filter_type").getAsString();
		JsonElement param = gson.get("parameters");
		ResponseEntity<Object> Entity = new ResponseEntity<Object>(HttpStatus.ACCEPTED);
		if (filterFiled.equals("likes")) {
			if (Filter.filterByLikes(service.getTweet(), filterType, param) == null) 
			{
				Entity = new ResponseEntity<Object>("Errore!",HttpStatus.BAD_REQUEST);
			}
			else
				Entity = new ResponseEntity<Object>(Filter.filterByLikes(service.getTweet(), filterType, param), HttpStatus.OK);

		}
		if (filterFiled.equals("retweets")) {
			if (Filter.filterByRetweet(service.getTweet(), filterType, param) == null) 
			{
				Entity = new ResponseEntity<Object>("Errore!",HttpStatus.BAD_REQUEST);
			}
			else
				Entity = new ResponseEntity<Object>(Filter.filterByRetweet(service.getTweet(), filterType, param), HttpStatus.OK);

		}
		if (filterFiled.equals("time")) {
			try {
				Entity = new ResponseEntity<Object>(Filter.filterByTime(service.getTweet(), filterType, param), HttpStatus.OK);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return Entity;
	}
	
	@RequestMapping(value = "/metadata", method = RequestMethod.GET, produces="application/json")
    String getMetadata(){
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonSchemaGenerator schemaGen = new JsonSchemaGenerator(mapper);
            com.fasterxml.jackson.module.jsonSchema.JsonSchema schema = schemaGen.generateSchema(Tweet.class);
            return mapper.writeValueAsString(schema);
        } catch (JsonProcessingException e){
        	return e.getLocalizedMessage();
        }
    }
}
