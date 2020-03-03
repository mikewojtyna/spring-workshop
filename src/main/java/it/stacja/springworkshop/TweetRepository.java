package it.stacja.springworkshop;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TweetRepository extends JpaRepository<Tweet, UUID> {

	Iterable<Tweet> findByTitle(String title);
}
