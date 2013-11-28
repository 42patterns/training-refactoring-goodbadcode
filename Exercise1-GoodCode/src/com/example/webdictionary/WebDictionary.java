package com.example.webdictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.webdictionary.iterators.DictPageIterator;


public class WebDictionary {
	List<DictionaryWord> savedWords = new ArrayList<DictionaryWord>();
	List<DictionaryWord> foundWords = new ArrayList<DictionaryWord>();

	public static void main(String[] args) {
		WebDictionary webDictionary = new WebDictionary();
		webDictionary.start();
	}

	public void start() {	
		processMenu();
	}
	
	private void processMenu() {
		boolean isRunning = true;
		Scanner scanner = new Scanner(System.in);
		printInfo();
		while (isRunning) {
			System.out.print("dictionary > ");
			String command = scanner.nextLine();
			if (command.equals("hello")) {
				printInfo();
			} else if (command.startsWith("search")) {
				searchWord(command);
			} else if (command.startsWith("save")) {
				saveWord(command);
			} else if (command.equals("showFound")) {
				showFoundWords();
			} else if (command.equals("showSaved")) {
				showSavedWords();
			} else if (command.equals("exit")) {
				isRunning = false;
			}
		}
		scanner.close();
	}

	protected void showSavedWords() {
		for (DictionaryWord el : savedWords) {
			System.out.println(el);
		}
	}

	protected void showFoundWords() {
		int i = 1;
		for (DictionaryWord el : foundWords) {
			System.out.println(i++ + ") " + el);
		}
	}

	protected void saveWord(String c) {
		int num = new Integer(c.split(" ")[1]);
		savedWords.add(foundWords.get(num - 1));
	}

	protected void searchWord(String c) {
		String[] commandParts = c.split(" ");
		String wordToFind = commandParts[1];
		
		SearchWordService searchService = new SearchWordService(new DictPageIterator(wordToFind));
		foundWords.addAll(searchService.search());
	}

	protected void printInfo() {
		System.out.println("Welcome to Web Dictionary System .");
	}
}