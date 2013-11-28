package com.example.webdictionary.iterators;

import java.util.Iterator;

public abstract class PageIterator implements Iterator<String> {

	public void remove() {
		throw new IllegalStateException("Not supported");
	}
}
