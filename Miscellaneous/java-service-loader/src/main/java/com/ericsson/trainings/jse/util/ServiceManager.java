package com.ericsson.trainings.jse.util;

import java.util.Iterator;
import java.util.ServiceLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceManager {
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceManager.class);

	private static final ServiceManager instance = new ServiceManager();

	private ServiceManager() {
	}

	public static ServiceManager getInstance() {
		return instance;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> T getService(final Class<T> clazz) {
		if (clazz == null) {
			throw new IllegalArgumentException("Unable to find implementation for null class");
		}

		final ServiceLoader<T> serviceLoader = ServiceLoader.load(clazz);
		final Iterator iter = serviceLoader.iterator();
		if (iter == null) {
			throw new IllegalStateException("Was not able to find any implementation for [" + clazz + "]. Please check your packaging!");
		}

		int count = 0;
		Object impl = null;
		while (iter.hasNext()) {
			impl = iter.next();
			count++;
		}
		if (impl == null) {
			throw new IllegalStateException("Was not able to find any implementation for [" + clazz + "]. Please check your packaging!");
		}
		if (count > 1) {
			LOGGER.warn("Found {} implementations of {}. Will use the latest one found {}", new Object[] { count, clazz, impl });
		}

		return (T) impl;
	}
}
