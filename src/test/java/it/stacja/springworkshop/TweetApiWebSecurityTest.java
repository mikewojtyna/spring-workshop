package it.stacja.springworkshop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TweetApiWebSecurityTest {

	@Autowired
	private MockMvc mockMvc;

	// @formatter:off
	@DisplayName(
		"when GET on /api/tweets without any credentials, " +
		"then unauthorized status is returned"
	)
	// @formatter:on
	@Test
	void unauthorized() throws Exception {
		// when
		mockMvc.perform(get("/api/tweets"))

			// then
			.andExpect(status().isUnauthorized());
	}

	// @formatter:off
	@DisplayName(
		"given test user credentials passed using http basic, " +
		"when GET on /api/tweets, " +
		"then OK status is returned"
	)
	// @formatter:on
	@Test
	void authorized() throws Exception {
		// given
		String testUser = "test_user";
		String testPassword = "test_password";

		// when
		mockMvc.perform(get("/api/tweets")
			.with(httpBasic(testUser, testPassword)))

			// then
			.andExpect(status().isOk());
	}
}
