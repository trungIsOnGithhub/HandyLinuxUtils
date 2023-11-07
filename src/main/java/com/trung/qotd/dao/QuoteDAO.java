package com.trung.qotd.dao;

import com.trung.qotd.entity.Quote;

import java.util.ArrayList;
import java.util.List;

public class QuoteDAO {
	private static List<Quote> quotes;
	
	public QuoteDAO() {
		if (quotes == null)
			quotes = new ArrayList<>();
	}

	public void saveOne(Quote quote) {
		quotes.add(quote);
	}
	
	public Quote getLatestOne(Quote quote) {
		int lastIndex = quotes.size() - 1;

		if (lastIndex < 0)
			return null;

		return quotes.get(lastIndex);
	}
}
