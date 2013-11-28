package com.example.webdictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebDictionary {
	private static List<String[]> savedWords = new ArrayList<String[]>();
	private static List<String[]> foundWords = new ArrayList<String[]>();

	public static void main(String[] args) {
		boolean isRunning = true;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Web Dictionary System .");
		
		while (isRunning) {
			System.out.print("dictionary > ");
			String command = scanner.nextLine();
			if (command.equals("hello")) {
				System.out.println("Welcome to Web Dictionary System .");
			} else if (command.startsWith("search")) {

				foundWords.clear();
				BufferedReader reader = null;
				String polishWord = null;
				String englishWord = null;
				int counter = 1;
				try {
					reader = new BufferedReader(new InputStreamReader(new URL(
							"http://www.dict.pl/dict?word=" + command.split(" ")[1]
									+ "&words=&lang=PL").openStream()));
					boolean polish = true;
					String line = null;
					Pattern pat = Pattern
							.compile(".*<a href=\"dict\\?words?=(.*)&lang.*");
					while ((line = reader.readLine()) != null) {
						Matcher matcher = pat.matcher(line);
						if (matcher.find()) {
							String foundWord = matcher.group(matcher.groupCount());
							if (polish) {
								System.out.print(counter + ") " + foundWord + " => ");
								polishWord = new String(foundWord.getBytes(), "UTF8");
								polish = false;
							} else {
								System.out.println(foundWord);
								polish = true;
								englishWord = new String(foundWord.getBytes(), "UTF8");
								foundWords.add(new String[] { polishWord, englishWord,
										new Date().toString() });
								counter++;
							}
						}
					}
				} catch (MalformedURLException ex) {
					ex.printStackTrace();
				} catch (IOException ex) {
					ex.printStackTrace();
				} finally {
					try {
						if (reader != null) {
							reader.close();
						}
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
							
			} else if (command.startsWith("save")) {
				int num = new Integer(command.split(" ")[1]);
				savedWords.add(foundWords.get(num - 1));
			} else if (command.equals("showFound")) {
				int i = 1;
				for (String[] el : foundWords) {
					System.out.println(i++ + ") " + Arrays.toString(el));
				}
			} else if (command.equals("showSaved")) {
				for (String[] el : savedWords) {
					System.out.println(Arrays.toString(el));
				}
			} else if (command.equals("exit")) {
				isRunning = false;
			}
		}
		scanner.close();
	}

}