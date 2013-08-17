package net.sf.video4j.gwt.client.presenter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import net.sf.video4j.gwt.client.event.ControlFullScreenEvent;
import net.sf.video4j.gwt.client.event.ControlMuteEvent;
import net.sf.video4j.gwt.client.event.ControlPauseEvent;
import net.sf.video4j.gwt.client.event.ControlPlayEvent;
import net.sf.video4j.gwt.client.event.ControlSeekedEvent;
import net.sf.video4j.gwt.client.event.ControlUnmuteEvent;
import net.sf.video4j.gwt.client.event.ControlVolumeChangeEvent;
import net.sf.video4j.gwt.client.model.PlayerParameters;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
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
    public void whenOnBind_shouldStartPlayerWithValidParameters() {
        mPresenter.onBind();
        ArgumentCaptor<PlayerParameters> oArgument = ArgumentCaptor.forClass(PlayerParameters.class);
        verify(mView).startPlayer(oArgument.capture());
        assertThat(oArgument.getValue(), is(notNullValue()));
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
    
}