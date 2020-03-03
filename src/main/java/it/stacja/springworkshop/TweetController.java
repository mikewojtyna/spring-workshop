package it.stacja.springworkshop;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/tweets")
public class TweetController {

	private TweetService tweetService;

	public TweetController(TweetService tweetService) {
		this.tweetService = tweetService;
	}

	@GetMapping("/{id}")
	public EntityModel<Tweet> singleTweet(@PathVariable UUID id) {
		Tweet tweet = tweetService.findById(id).orElse(null);
		EntityModel<Tweet> entityModel = wrapWithSelfRel(tweet);
		return entityModel;
	}

	@GetMapping
	public CollectionModel<EntityModel<Tweet>> allTweets() {
		return new CollectionModel<>(tweetService.allTweets().stream()
			.map(this::wrapWithSelfRel).collect(Collectors
				.toList()),
			linkTo(methodOn(TweetController.class)
			.allTweets()).withSelfRel());
	}

	@GetMapping(params = "title")
	public Iterable<Tweet> findTweetByTitle(@RequestParam("title") String title) {
		return tweetService.findByTitle(title);
	}

	@PostMapping
	public void create(@RequestBody Tweet tweet) {
		tweetService.create(tweet);
	}

	private EntityModel<Tweet> wrapWithSelfRel(Tweet tweet) {
		return new EntityModel<>(tweet,
			linkTo(methodOn(TweetController.class)
			.singleTweet(tweet.getId())).withSelfRel());
	}
}
