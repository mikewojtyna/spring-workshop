package it.stacja.springworkshop;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tweets")
public class TweetController {

	private TweetRepository tweetRepository;

	public TweetController(TweetRepository tweetRepository) {
		this.tweetRepository = tweetRepository;
	}

	@GetMapping
	public Iterable<Tweet> allTweets() {
		return tweetRepository.findAll();
	}

	@GetMapping(params = "title")
	public Iterable<Tweet> findTweetByTitle(@RequestParam("title") String title) {
		return tweetRepository.findByTitle(title);
	}

	@PostMapping
	public void create(@RequestBody Tweet tweet) {
		tweetRepository.save(tweet);
	}
}
