package it.stacja.springworkshop;

public interface TweetService {

	Iterable<Tweet> allTweets();

	Iterable<Tweet> findByTitle(String title);

	void create(Tweet tweet);
}
