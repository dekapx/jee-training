package com.ericsson.trainings.jse.weld.cdi.util;

import java.util.Iterator;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

@ApplicationScoped
public enum BeanFactory {
	INSTANCE;

	private BeanManager beanManager;

	BeanFactory() {
		final WeldContainer weldContainer = new Weld().initialize();
		beanManager = weldContainer.getBeanManager();
	}

	@SuppressWarnings({ "unchecked" })
	public <T> T getBeanInstanceByName(final String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("bean name must not be null or empty");
		}

		final Iterator<Bean<?>> iterator = beanManager.getBeans(name).iterator();
		final Bean<T> bean = (Bean<T>) iterator.next();
		final CreationalContext<T> context = beanManager.createCreationalContext(bean);
		return (T) beanManager.getReference(bean, bean.getBeanClass(), context);
	}
}
