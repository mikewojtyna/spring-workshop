package it.stacja.springworkshop;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface TweetService {

	Collection<Tweet> allTweets();

	Iterable<Tweet> findByTitle(String title);

	void create(Tweet tweet);

	Optional<Tweet> findById(UUID id);
}
