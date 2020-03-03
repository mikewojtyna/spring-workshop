package it.stacja.springworkshop;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TweetRepository extends CrudRepository<Tweet, UUID> {

	Iterable<Tweet> findByTitle(String title);
}
