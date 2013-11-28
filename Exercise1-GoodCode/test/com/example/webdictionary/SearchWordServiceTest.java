package com.example.webdictionary;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.example.webdictionary.DictionaryWord;
import com.example.webdictionary.SearchWordService;
import com.example.webdictionary.iterators.DictPageIterator;


@RunWith(Parameterized.class)
public class SearchWordServiceTest {

	private String searchedWord;
	private String firstAnswers;
	private String secondAnswers;
	private String lastAnswers;
	private int totalSize;

	public SearchWordServiceTest(String searchedWord, String firstAnswers,
			String secondAnswers, String lastAnswers, int totalSize) {
		super();
		this.searchedWord = searchedWord;
		this.firstAnswers = firstAnswers;
		this.secondAnswers = secondAnswers;
		this.lastAnswers = lastAnswers;
		this.totalSize = totalSize;
	}

	@Parameters
	public static List<Object[]> data() {
		return Arrays
				.asList(new Object[][] {
						{
								"book",
								"bloczek",
								"książka",
								"biblia (termin określający pewien zestaw książek zawierających elementarną wiedzę np. %27Knuth%27, %27K%26R %27, %27Camel Book %27 )",
								24 },
						{
								"hello",
								"cześć; witaj",
								"halo!",
								"gwizdówka białoskrzydła; Peneothello sigillatus (gatunek ptaka)",
								9 } });
	}

	@Test
	public void searchWordTest() {
		// when
		SearchWordService service = new SearchWordService(new DictPageIterator(searchedWord));
		List<DictionaryWord> list = service.search();

		// then
		assertThat(list.size(), is(equalTo(totalSize)));
		assertThat(list.get(0).getPolishWord(), is(equalTo(firstAnswers)));
		assertThat(list.get(1).getPolishWord(), is(equalTo(secondAnswers)));

		int lastItem = totalSize - 1;
		assertThat(list.get(lastItem).getPolishWord(), is(equalTo(lastAnswers)));
	}
}
