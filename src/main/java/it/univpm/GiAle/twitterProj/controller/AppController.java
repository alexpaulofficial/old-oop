package it.univpm.GiAle.twitterProj.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;

import javax.swing.text.html.parser.Entity;
import javax.xml.ws.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import it.univpm.GiAle.twitterProj.filters.Filter;
import it.univpm.GiAle.twitterProj.filters.Stats;
import it.univpm.GiAle.twitterProj.model.Tweet;
import it.univpm.GiAle.twitterProj.service.TweetService;

@RestController
public class AppController {
	@Autowired
	TweetService service;

	@RequestMapping(value = "/data", method = RequestMethod.GET)
	public ResponseEntity<Object> getTweet() {
		return new ResponseEntity<>(service.getTweet(), HttpStatus.OK);
	}

	@PostMapping("/data")
	public ResponseEntity<Object> postJSONBody(@RequestBody String body) {

		service.addTweetsArray(service.addJson(body));
		return new ResponseEntity<>(
				"Tweet caricati correttamente. Per verificare, fare una richiesta GET allo stesso indirizzo",
				HttpStatus.OK);
	}
	@PostMapping("/data/twitter")
	public ResponseEntity<Object> postFromTwitter(@RequestBody String url) throws MalformedURLException, IOException {
		
		service.addTweetsArray(service.addJson(service.GetFromTwitter(url)));
		return new ResponseEntity<>(
				"Tweet scaricati correttamente da twitter. Per verificare, fare una richiesta GET allo stesso indirizzo",
				HttpStatus.OK);
	}

	@GetMapping("/data/filter")
	public ResponseEntity<Object> filter(@RequestBody String bodyFilter) {
		
		return new ResponseEntity<Object> (service.filtraggio(bodyFilter, service.getTweet()), HttpStatus.OK);
	}

	@GetMapping("/data/stats")
	public ResponseEntity<Object> stats(@RequestBody String bodyFilter) {
		return new ResponseEntity<Object> (Stats.stats(service.getTweet()), HttpStatus.OK);
	}

	@RequestMapping(value = "/metadata", method = RequestMethod.GET, produces = "application/json")
	String getMetadata() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonSchemaGenerator schemaGen = new JsonSchemaGenerator(mapper);
			com.fasterxml.jackson.module.jsonSchema.JsonSchema schema = schemaGen.generateSchema(Tweet.class);
			return mapper.writeValueAsString(schema);
		} catch (JsonProcessingException e) {
			return e.getLocalizedMessage();
		}
	}
}
