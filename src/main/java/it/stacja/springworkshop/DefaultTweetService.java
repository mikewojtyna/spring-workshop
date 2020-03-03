package it.stacja.springworkshop;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class DefaultTweetService implements TweetService {

	private TweetRepository tweetRepository;
	private TweetSafetyStrategy safetyStrategy;

	public DefaultTweetService(TweetRepository tweetRepository,
				   TweetSafetyStrategy safetyStrategy) {
		this.tweetRepository = tweetRepository;
		this.safetyStrategy = safetyStrategy;
	}

	@Override
	public Collection<Tweet> allTweets() {
		return tweetRepository.findAll();
	}

	@Override
	public Iterable<Tweet> findByTitle(String title) {
		return tweetRepository.findByTitle(title);
	}

	@Override
	public void create(Tweet tweet) {
		if (safetyStrategy.isSafe(tweet)) {
			tweetRepository.save(tweet);
		}
	}

	@Override
	public Optional<Tweet> findById(UUID id) {
		return tweetRepository.findById(id);
	}
}
