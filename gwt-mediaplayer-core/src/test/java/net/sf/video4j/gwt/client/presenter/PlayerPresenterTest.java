package net.sf.video4j.gwt.client.presenter;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import net.sf.video4j.gwt.client.event.ControlFullScreenEvent;
import net.sf.video4j.gwt.client.event.ControlMuteEvent;
import net.sf.video4j.gwt.client.event.ControlPauseEvent;
import net.sf.video4j.gwt.client.event.ControlPlayEvent;
import net.sf.video4j.gwt.client.event.ControlSeekedEvent;
import net.sf.video4j.gwt.client.event.ControlUnmuteEvent;
import net.sf.video4j.gwt.client.event.ControlVolumeChangeEvent;
import net.sf.video4j.gwt.client.event.PlaylistPlayEvent;
import net.sf.video4j.gwt.client.model.PlayerParameters;
import net.sf.video4j.gwt.client.player.Media;
import net.sf.video4j.gwt.client.player.PlayItem;

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
public class PlayerPresenterTest {

    private PlayerPresenter mPresenter;
    
    @Mock
    private PlayerPresenter.PView mView;
    @Mock
    private EventBus mEventBus;
    
    @Before
    public void setUp() throws Exception {
        mPresenter = new PlayerPresenter(mEventBus, mView);
    }
    
	@Ignore
    @Test
	public void whenOnBind_shouldRegisterHandlers() {
		mPresenter.onBind();

		verify(mEventBus).addHandler(eq(ControlPlayEvent.getType()), eq(mPresenter));
//		verify(mEventBus).addHandler(eq(ControlPauseEvent.getType()), eq(mPresenter));
//		verify(mEventBus).addHandler(eq(ControlMuteEvent.getType()), eq(mPresenter));
//		verify(mEventBus).addHandler(eq(ControlUnmuteEvent.getType()), eq(mPresenter));
//		verify(mEventBus).addHandler(eq(ControlSeekedEvent.getType()), eq(mPresenter));
//		verify(mEventBus).addHandler(eq(ControlFullScreenEvent.getType()), eq(mPresenter));
//		verify(mEventBus).addHandler(eq(ControlVolumeChangeEvent.getType()), eq(mPresenter));
//		verify(mEventBus).addHandler(eq(ApplicationLoadEvent.getType()), eq(mPresenter));
//		verify(mEventBus).addHandler(eq(ApplicationInitEvent.getType()), eq(mPresenter));
//		verify(mEventBus).addHandler(eq(PlaylistPlayEvent.getType()), eq(mPresenter));
	}

	@Test
    public void whenOnControlPlayEvent_shouldPlayInView() {
        ControlPlayEvent oEvent = mock(ControlPlayEvent.class);
        mPresenter.onControlPlayEvent(oEvent);
        verify(mView).play();
    }
    
    @Test
    public void whenOnControlPauseEvent_shouldPauseInView() {
        ControlPauseEvent oEvent = mock(ControlPauseEvent.class);
        mPresenter.onControlPauseEvent(oEvent);
        verify(mView).pause();
    }
    
    @Test
    public void whenOnControlVolumeChangeEvent_shouldChangeVolumeInView() {
        ControlVolumeChangeEvent oEvent = mock(ControlVolumeChangeEvent.class);
        mPresenter.onControlVolumeChangeEvent(oEvent);
        verify(mView).volume(anyDouble());
    }
    
    @Test
    public void whenOnControlFullScreenEvent_shouldCallFullScreenInView() {
        ControlFullScreenEvent oEvent = mock(ControlFullScreenEvent.class);
        mPresenter.onControlFullScreenEvent(oEvent);
        verify(mView).fullScreen();
    }
    
    @Test
    public void whenOnControlSeekedEvent_shouldSeekInView() {
        ControlSeekedEvent oEvent = mock(ControlSeekedEvent.class);
        mPresenter.onControlSeekedEvent(oEvent);
        verify(mView).seek(anyDouble());
    }
    
    @Test
    public void whenOnControlUnmuteEvent_shouldUnmuteInView() {
        ControlUnmuteEvent oEvent = mock(ControlUnmuteEvent.class);
        mPresenter.onControlUnmuteEvent(oEvent);
        verify(mView).unmute();
    }
    
    @Test
    public void whenOnControlMuteEvent_shouldMuteInView() {
        ControlMuteEvent oEvent = mock(ControlMuteEvent.class);
        mPresenter.onControlMuteEvent(oEvent);
        verify(mView).mute();
    }
    
	@Test
	public void givenAdMedia_shouldNotPlay() {
		PlaylistPlayEvent oEvent = mock(PlaylistPlayEvent.class);

		Media oMedia = new Media();
		oMedia.setAd(true);
		when(oEvent.getPlayItem()).thenReturn(new PlayItem(oMedia));

		mPresenter.onPlaylistPlayEvent(oEvent);

		verify(mView, never()).startPlayer(any(PlayerParameters.class));
	}

	@Test
	public void givenNonAdMedia_shouldPlay() {
		PlaylistPlayEvent oEvent = mock(PlaylistPlayEvent.class);

		Media oMedia = new Media();
		oMedia.setAd(false);
		when(oEvent.getPlayItem()).thenReturn(new PlayItem(oMedia));

		mPresenter.onPlaylistPlayEvent(oEvent);

		verify(mView).startPlayer(any(PlayerParameters.class));
	}

}