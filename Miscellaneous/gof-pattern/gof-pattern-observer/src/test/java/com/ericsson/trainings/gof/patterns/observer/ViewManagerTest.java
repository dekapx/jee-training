package com.ericsson.trainings.gof.patterns.observer;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;

import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

public class ViewManagerTest {
	private IView mockedOutlineView;
	private IView mockedPropertyView;

	private ViewManager viewManager;

	@Before
	public void setup() {
		mockedOutlineView = mock(OutlineView.class);
		mockedPropertyView = mock(PropertyView.class);
	}

	@After
	public void teardown() {
		viewManager = null;
	}

	@Test(expected = IllegalArgumentException.class)
	public void addView_withNullArgument_throwsIllegalArgumentException() {
		viewManager = new ViewManagerImpl();
		viewManager.addView(null);
	}

	@Test
	public void addView_withValidIViewImplementations_addSuccessfully() {
		viewManager = new ViewManagerImpl();

		viewManager.addView(mockedOutlineView);
		viewManager.addView(mockedPropertyView);

		Collection<IView> views = Whitebox.getInternalState(viewManager, "views");
		assertNotNull(views);
		assertTrue(views.size() == 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void removeView_withNullArgument_throwsIllegalArgumentException() {
		viewManager = new ViewManagerImpl();
		viewManager.removeView(null);
	}

	@Test
	public void removeView_withValidIViewImplementations_removeSuccessfully() {
		viewManager = new ViewManagerImpl();

		viewManager.addView(mockedOutlineView);
		viewManager.addView(mockedPropertyView);

		Collection<IView> views = Whitebox.getInternalState(viewManager, "views");
		assertNotNull(views);
		assertTrue(views.size() == 2);

		viewManager.removeView(mockedOutlineView);
		assertTrue(views.size() == 1);

		viewManager.removeView(mockedPropertyView);
		assertTrue(views.size() == 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setEvent_withNullArgument_throwsIllegalArgumentException() {
		viewManager = new ViewManagerImpl();
		viewManager.setEvent(null);
	}

	@Test
	public void setEvent_withValidEvent_notifyAllObservers() {
		viewManager = new ViewManagerImpl();

		viewManager.addView(mockedOutlineView);
		viewManager.addView(mockedPropertyView);

		Collection<IView> views = Whitebox.getInternalState(viewManager, "views");
		assertNotNull(views);
		assertTrue(views.size() == 2);

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
