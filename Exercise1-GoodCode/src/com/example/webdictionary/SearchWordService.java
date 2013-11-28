package com.example.webdictionary;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.webdictionary.iterators.PageIterator;


public class SearchWordService {

	private PageIterator iterator;
	
	public SearchWordService(PageIterator iterator) {
		this.iterator = iterator;
	}

	public List<DictionaryWord> search() {
		List<DictionaryWord> words = new ArrayList<DictionaryWord>();

		int counter = 1;
		while (iterator.hasNext()) {
			DictionaryWord word = new DictionaryWord();
			word.setDate(new Date());
			word.setPolishWord(iterator.next());
			word.setEnglishWord(iterator.next());

			words.add(word);

			System.out.println(counter + ") " + word.getPolishWord() + " => "
					+ word.getEnglishWord());
			counter++;
		}

		return words;
	}

}
