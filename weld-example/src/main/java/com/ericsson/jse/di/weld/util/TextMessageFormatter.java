package com.ericsson.jse.di.weld.util;

public class TextMessageFormatter implements MessageFormatter {

	@Override
	public String format(String text) {
		return "Hello !!! " + text;
	}

}
