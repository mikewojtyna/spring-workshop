package it.stacja.springworkshop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static it.stacja.springworkshop.TweetTestUtils.tweetWithTitle;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DefaultTweetServiceIntegrationTest {

	@Autowired
	private TweetService tweetService;

	// @formatter:off
	@DisplayName(
		"create new tweet"
	)
	// @formatter:on
	@Test
	void test() throws Exception {
		// given
		Tweet tweet = tweetWithTitle("title0");

		// when
		tweetService.create(tweet);

		// then
		Iterable<Tweet> tweets = tweetService.allTweets();
		assertThat(tweets).hasSize(1).extracting("title")
			.containsOnly("title0");
	}
}
