package it.stacja.springworkshop;

public class DefaultTweetService implements TweetService {

	private TweetRepository tweetRepository;
	private TweetSafetyStrategy safetyStrategy;

	public DefaultTweetService(TweetRepository tweetRepository,
				   TweetSafetyStrategy safetyStrategy) {
		this.tweetRepository = tweetRepository;
		this.safetyStrategy = safetyStrategy;
	}

	@Override
	public Iterable<Tweet> allTweets() {
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
}
