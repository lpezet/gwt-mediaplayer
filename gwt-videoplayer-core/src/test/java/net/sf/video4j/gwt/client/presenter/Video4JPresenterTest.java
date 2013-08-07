package net.sf.video4j.gwt.client.presenter;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import net.sf.video4j.gwt.client.controller.ApplicationController;
import net.sf.video4j.gwt.client.presenter.ControlPresenter.CView;
import net.sf.video4j.gwt.client.presenter.PlayerPresenter.PView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.testing.CountingEventBus;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * @author gumatias
 */
@RunWith(MockitoJUnitRunner.class)
public class Video4JPresenterTest {
    
    private Video4JPresenter mPresenter;
    private EventBus mEventBus;
    
    @Mock
    private Video4JPresenter.V4JView mView;
    
    @Before
    public void setUp() throws Exception {
    	mEventBus = new CountingEventBus();
    	mPresenter = new Video4JPresenter(
    			mEventBus, 
    			mView,
    			null,
    			new PlayerPresenter(mEventBus, mock(PView.class)), 
    			new ControlPresenter(mEventBus, mock(CView.class)), 
    			new ApplicationController(mEventBus));
    }

    @Test
    public void whenOnBind_shouldSetExpectedSlotsInView() {
        mPresenter.onBind();
        verify(mView, times(2)).setInSlot(anyObject(), any(IsWidget.class));
        verify(mView).setInSlot(anyObject(), isA(PlayerPresenter.class));
        verify(mView).setInSlot(anyObject(), isA(ControlPresenter.class));
    }
    
}