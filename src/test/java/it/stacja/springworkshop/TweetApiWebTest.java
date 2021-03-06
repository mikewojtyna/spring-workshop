package it.stacja.springworkshop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TweetController.class)
@WithMockUser
public class TweetApiWebTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private TweetService tweetService;

	// @formatter:off
	@DisplayName(
		"when GET on /api/tweets, " +
		"then 200 status is returned and a list of tweets is returned" +
		" as well"
	)
	// @formatter:on
	@Test
	void okStatus() throws Exception {
		// given
		when(tweetService.allTweets()).thenReturn(List
			.of(tweetWithTitle("title0"), tweetWithTitle("title1")
				, tweetWithTitle("title2")));

		// when
		mockMvc.perform(get("/api/tweets"))

			// then
			.andExpect(status().isOk())
			.andExpect(jsonPath("$._embedded.tweetList",
				hasSize(3)));
		verify(tweetService).allTweets();
	}

	// @formatter:off
	@DisplayName(
		"when POST on /api/tweets with tweet data, " +
		"then create command is invoked with the same data"
	)
	// @formatter:on
	@Test
	void create() throws Exception {
		// given
		// @formatter:off
		String tweetJson =
			"{ \"title\": \"My First tweet\" }";
		// @formatter:on

		// when
		mockMvc.perform(post("/api/tweets").content(tweetJson)
			.contentType(MediaType.APPLICATION_JSON));

		// then
		Tweet tweet = tweetWithTitle("My First tweet");
		verify(tweetService).create(tweet);
	}

	// @formatter:off
	@DisplayName(
		"when GET on /api/tweets with param title = 'title1', " +
		"then only tweet with title 'title1' is returned"
	)
	// @formatter:on
	@Test
	void searchByTitle() throws Exception {
		// given
		when(tweetService.findByTitle("title1"))
			.thenReturn(List.of(tweetWithTitle("title1")));

		// when
		mockMvc.perform(get("/api/tweets").param("title", "title1"))

			// then
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(1)))
			.andExpect(jsonPath("$[0].title", is("title1")));
		verify(tweetService).findByTitle("title1");
	}

	private Tweet tweetWithTitle(String title) {
		return TweetTestUtils.tweetWithTitle(title);
	}
}
