package com.ericsson.trainings.jee.weld.cdi.util;

import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class BeanFactory {
	private static final Logger LOGGER = LoggerFactory.getLogger(BeanFactory.class);

	private BeanManager beanManager;

	@PostConstruct
	public void setup() {
		tryLookupBeanManagerInJndi();
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

	private void tryLookupBeanManagerInJndi() {
		try {
			LOGGER.debug("Trying to find BeanManager in JNDI");
			final InitialContext ctx = new InitialContext();
			final BeanManager lookupBeanManager = (BeanManager) ctx.lookup("java:comp/BeanManager");
			if (lookupBeanManager != null) {
				LOGGER.debug("Successfully found BeanManager {}", lookupBeanManager);
				beanManager = lookupBeanManager;
			}
		} catch (final NamingException ignored) {
			LOGGER.warn("Was not able to find BeanManager in JNDI. Details {}", ignored.getMessage());
		}
	}
}
