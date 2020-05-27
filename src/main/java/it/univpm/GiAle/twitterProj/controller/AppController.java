package it.univpm.GiAle.twitterProj.controller;

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
	@GetMapping("/data/filter/likes/{filter}:{likes}")
	public ResponseEntity<Object> filterLikes (@PathVariable("likes") int likes, @PathVariable("filter") String filter) {
		
		return new ResponseEntity<> (Filter.filterByLikes(service.getTweet(), filter, likes), HttpStatus.OK);
	}
	@GetMapping("/data/filter/retweets/{filter}:{retweet}")
	public ResponseEntity<Object> filterRetweet (@PathVariable("retweet") int retweet, @PathVariable("filter") String filter) {
		return new ResponseEntity<> (Filter.filterByRetweet(service.getTweet(), filter, retweet), HttpStatus.OK);
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
