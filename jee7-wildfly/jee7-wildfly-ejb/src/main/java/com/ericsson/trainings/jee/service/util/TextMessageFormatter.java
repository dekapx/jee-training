package com.ericsson.trainings.jee.service.util;

import javax.enterprise.inject.Default;

@Default
public class TextMessageFormatter {
	public String format(final String text) {
		return "Hello !!! " + text;
	}
}
