package it.stacja.springworkshop;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tweets")
public class TweetController {

	private TweetService tweetService;

	public TweetController(TweetService tweetService) {
		this.tweetService = tweetService;
	}

	@GetMapping
	public Iterable<Tweet> allTweets() {
		return tweetService.allTweets();
	}

	@GetMapping(params = "title")
	public Iterable<Tweet> findTweetByTitle(@RequestParam("title") String title) {
		return tweetService.findByTitle(title);
	}

	@PostMapping
	public void create(@RequestBody Tweet tweet) {
		tweetService.create(tweet);
	}
}
