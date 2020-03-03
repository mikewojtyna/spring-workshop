package it.stacja.springworkshop;

public class TweetTestUtils {
	public static Tweet tweetWithTitle(String title) {
		Tweet tweet = new Tweet();
		tweet.setTitle(title);
		return tweet;
	}
}
