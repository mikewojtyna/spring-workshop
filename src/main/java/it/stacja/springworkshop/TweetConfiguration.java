package it.stacja.springworkshop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TweetConfiguration {

	@Bean
	public TweetService tweetService(TweetRepository tweetRepository) {
		return new DefaultTweetService(tweetRepository, t -> true);
	}
}
