package com.ericsson.trainings.jse.weld.cdi.util;

public class TextMessageFormatter implements MessageFormatter {

	@Override
	public String format(String text) {
		return "Hello !!! " + text;
	}

}
