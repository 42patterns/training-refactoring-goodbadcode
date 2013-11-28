package com.example.webdictionary;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DictionaryWord {

	private String polishWord;
	private String englishWord;
	private Date date;

	public DictionaryWord() {

	}

	public DictionaryWord(String polishWord, String englishWord, Date date) {
		super();
		this.polishWord = polishWord;
		this.englishWord = englishWord;
		this.date = date;
	}

	public String getPolishWord() {
		return polishWord;
	}

	public void setPolishWord(String polishWord) {
		this.polishWord = polishWord;
	}

	public String getEnglishWord() {
		return englishWord;
	}

	public void setEnglishWord(String englishWord) {
		this.englishWord = englishWord;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return "[" + polishWord + ", " + englishWord + ", " + sdf.format(date) + "]";
	}

}
