package net.sf.video4j.gwt.client.presenter;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import net.sf.video4j.gwt.client.event.ApplicationInitEvent;
import net.sf.video4j.gwt.client.event.ApplicationLoadEvent;
import net.sf.video4j.gwt.client.event.ControlFullScreenEvent;
import net.sf.video4j.gwt.client.event.ControlMuteEvent;
import net.sf.video4j.gwt.client.event.ControlPauseEvent;
import net.sf.video4j.gwt.client.event.ControlPlayEvent;
import net.sf.video4j.gwt.client.event.ControlSeekedEvent;
import net.sf.video4j.gwt.client.event.ControlUnmuteEvent;
import net.sf.video4j.gwt.client.event.ControlVolumeChangeEvent;
import net.sf.video4j.gwt.client.event.PlaylistPlayEvent;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.gwt.event.shared.EventBus;

/**
 * @author gumatias
 */
@RunWith(MockitoJUnitRunner.class)
public class BasePlayerPresenterTest {

	private DummyBasePlayerPresenter		mPresenter;
    
    @Mock
	private DummyBasePlayerPresenter.DView	mView;
    @Mock
    private EventBus mEventBus;
    
    @Before
    public void setUp() throws Exception {
		mPresenter = new DummyBasePlayerPresenter(mEventBus, mView);
    }
    
	// FIXME: need to figure out how to verify that all handlers have been registered to the presenter
	@Ignore
    @Test
	public void whenOnBind_shouldRegisterHandlers() {
		mPresenter.onBind();

		verify(mEventBus).addHandler(eq(ControlPlayEvent.getType()), eq(mPresenter));
		verify(mEventBus).addHandler(eq(ControlPauseEvent.getType()), eq(mPresenter));
		verify(mEventBus).addHandler(eq(ControlMuteEvent.getType()), eq(mPresenter));
		verify(mEventBus).addHandler(eq(ControlUnmuteEvent.getType()), eq(mPresenter));
		verify(mEventBus).addHandler(eq(ControlSeekedEvent.getType()), eq(mPresenter));
		verify(mEventBus).addHandler(eq(ControlFullScreenEvent.getType()), eq(mPresenter));
		verify(mEventBus).addHandler(eq(ControlVolumeChangeEvent.getType()), eq(mPresenter));
		verify(mEventBus).addHandler(eq(ApplicationLoadEvent.getType()), eq(mPresenter));
		verify(mEventBus).addHandler(eq(ApplicationInitEvent.getType()), eq(mPresenter));
		verify(mEventBus).addHandler(eq(PlaylistPlayEvent.getType()), eq(mPresenter));
	}

}