package com.ericsson.trainings.gof.patterns.observer;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.reflect.Whitebox;

public class ViewManagerTest {
	private IView mockedOutlineView;
	private IView mockedPropertyView;

	private ViewManager viewManager;

	@Before
	public void setup() {
		mockedOutlineView = PowerMockito.mock(OutlineView.class);
		mockedPropertyView = PowerMockito.mock(PropertyView.class);
	}

	@After
	public void teardown() {
		viewManager = null;
	}

	@Test
	public void testAddView() {
		viewManager = new ViewManagerImpl();

		viewManager.addView(mockedOutlineView);
		viewManager.addView(mockedPropertyView);

		Collection<IView> views = Whitebox.getInternalState(viewManager, "views");
		Assert.assertNotNull(views);
		Assert.assertTrue(views.size() == 2);
	}

	@Test
	public void testRemoveView() {
		viewManager = new ViewManagerImpl();

		viewManager.addView(mockedOutlineView);
		viewManager.addView(mockedPropertyView);

		Collection<IView> views = Whitebox.getInternalState(viewManager, "views");
		Assert.assertNotNull(views);
		Assert.assertTrue(views.size() == 2);

		viewManager.removeView(mockedOutlineView);
		Assert.assertTrue(views.size() == 1);

		viewManager.removeView(mockedPropertyView);
		Assert.assertTrue(views.size() == 0);
	}

	@Test
	public void testSetEvent() {
		viewManager = new ViewManagerImpl();

		viewManager.addView(mockedOutlineView);
		viewManager.addView(mockedPropertyView);

		Collection<IView> views = Whitebox.getInternalState(viewManager, "views");
		Assert.assertNotNull(views);
		Assert.assertTrue(views.size() == 2);

		Event javaFileEvent = new Event("Java");
		viewManager.setEvent(javaFileEvent);

		verify(mockedOutlineView, times(1)).refresh(javaFileEvent);
		verify(mockedPropertyView, times(1)).refresh(javaFileEvent);

		Event xmlFileEvent = new Event("XML");
		viewManager.setEvent(xmlFileEvent);

		verify(mockedOutlineView, times(1)).refresh(xmlFileEvent);
		verify(mockedPropertyView, times(1)).refresh(xmlFileEvent);
	}
}
