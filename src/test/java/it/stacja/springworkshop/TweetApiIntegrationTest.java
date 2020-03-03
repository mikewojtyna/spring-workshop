package it.stacja.springworkshop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TweetApiIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	// @formatter:off
	@DisplayName(
		"when GET on /api/tweets, " +
		"then 200 status is returned"
	)
	// @formatter:on
	@Test
	void okStatus() throws Exception {
		// when
		mockMvc.perform(get("/api/tweets"))

			// then
			.andExpect(status().isOk());
	}

	// @formatter:off
	@DisplayName(
		"given one tweet with title 'My First tweet'" +
		"when POST on /api/tweets with given data, " +
		"then tweet is created" +
		"and GET on /api/tweets returns a list " +
		"containing single 'My First tweet'"
	)
	// @formatter:on
	@Test
	void create() throws Exception {
		// given
		// @formatter:off
		String firstTweetDataAsJson =
			"{ \"title\": \"My First tweet\" }";
		// @formatter:on

		// when
		mockMvc.perform(post("/api/tweets")
			.content(firstTweetDataAsJson)
			.contentType(MediaType.APPLICATION_JSON));

		// then
		mockMvc.perform(get("/api/tweets"))
			.andExpect(jsonPath("$[0].title", is("My First " +
				"tweet")));
	}

	// @formatter:off
	@DisplayName(
		"given three tweets with titles 'title0', 'title1' and " +
			"'title2', " +
		"when GET on /api/tweets with param title = 'title1', " +
		"then only tweet with title 'title1' is returned"
	)
	// @formatter:on
	@Test
	void searchByTitle() throws Exception {
		// given
		// @formatter:off
		String tweet0 =
			"{ \"title\": \"title0\" }";
		String tweet1 =
			"{ \"title\": \"title1\" }";
		String tweet2 =
			"{ \"title\": \"title2\" }";
		// @formatter:on
		mockMvc.perform(post("/api/tweets").content(tweet0)
			.contentType(MediaType.APPLICATION_JSON));
		mockMvc.perform(post("/api/tweets").content(tweet1)
			.contentType(MediaType.APPLICATION_JSON));
		mockMvc.perform(post("/api/tweets").content(tweet2)
			.contentType(MediaType.APPLICATION_JSON));

		// when
		mockMvc.perform(get("/api/tweets").param("title", "title1"))

			// then
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(1)))
			.andExpect(jsonPath("$[0].title", is("title1")));
	}
}
