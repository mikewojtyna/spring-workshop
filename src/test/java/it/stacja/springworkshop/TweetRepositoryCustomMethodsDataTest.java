package it.stacja.springworkshop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import static it.stacja.springworkshop.TweetTestUtils.tweetWithTitle;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TweetRepositoryCustomMethodsDataTest {

	@Autowired
	private TweetRepository tweetRepository;

	// @formatter:off
	@DisplayName(
		"given two tweets in the database, " +
		"when search by title, " +
		"then only tweet with given title is found"
	)
	// @formatter:on
	@Test
	void test() throws Exception {
		// given
		tweetRepository.save(tweetWithTitle("title0"));
		tweetRepository.save(tweetWithTitle("title1"));
		tweetRepository.save(tweetWithTitle("title2"));

		// when
		Iterable<Tweet> foundTweets = tweetRepository
			.findByTitle("title1");

		// then
		assertThat(foundTweets).hasSize(1)
			.extracting("title").containsOnly("title1");
	}
}

