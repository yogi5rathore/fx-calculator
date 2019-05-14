package com.anz.fx.calculator.constant;

public enum FeedEnum {
	DIRECT("D"), INVERSION("Inv"), UNITY("1:1");

	private String feed;

	public String getFeed() {
		return this.feed;
	}

	FeedEnum(String feed) {
		this.feed = feed;
	}

	public static boolean contains(String value) {

		for (FeedEnum feed : FeedEnum.values()) {
			if (feed.getFeed().equals(value)) {
				return true;
			}
		}

		return false;
	}

	public static FeedEnum findByAbbreviation(String value) {
		for (FeedEnum feed : FeedEnum.values()) {
			if (feed.getFeed().equals(value)) {
				return feed;
			}
		}
		return null;
	}
}
