package net.sf.video4j.gwt.client.presenter;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Random;

import net.sf.video4j.gwt.client.controller.ApplicationController;
import net.sf.video4j.gwt.client.controller.BandwidthController;
import net.sf.video4j.gwt.client.controller.PlaylistController;
import net.sf.video4j.gwt.client.util.PlayItemBeanFactory;

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
    @Mock
    private ApplicationController mApplicationController;
    @Mock
    private ControlPresenter mControlPresenter;
    @Mock
    private PlayerPresenter mPlayerPresenter;
    @Mock
    private AdPresenter mAdPresenter;
    @Mock
    private PlayItemBeanFactory mPlayItemBeanFactory;
    
    @Before
    public void setUp() throws Exception {
    	mEventBus = new CountingEventBus();
    	Random oRandom = new Random();
    	mPresenter = new Video4JPresenter(
    			mEventBus, 
    			mView,
    			null,
    			mPlayerPresenter, 
    			mControlPresenter,
    			mAdPresenter,
    			mApplicationController,
    			new PlaylistController(mEventBus, mPlayItemBeanFactory),
    			new BandwidthController(mEventBus, oRandom));
    }

    @Test
    public void whenOnBind_shouldSetExpectedSlotsInView() {
        mPresenter.onBind();
        
        verify(mView, times(3)).setInSlot(anyObject(), any(IsWidget.class));
        verify(mView).setInSlot(anyObject(), isA(PlayerPresenter.class));
        verify(mView).setInSlot(anyObject(), isA(ControlPresenter.class));
		verify(mView).setInSlot(anyObject(), isA(AdPresenter.class));
    }
    
}