package net.sf.video4j.gwt.client.presenter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;

import net.sf.video4j.gwt.client.config.model.Playlist;
import net.sf.video4j.gwt.client.config.model.Track;
import net.sf.video4j.gwt.client.config.model.Video4JConfig;
import net.sf.video4j.gwt.client.controller.ApplicationController;
import net.sf.video4j.gwt.client.controller.PlaylistController;
import net.sf.video4j.gwt.client.model.ApplicationConfig;
import net.sf.video4j.gwt.client.presenter.ControlPresenter.CView;
import net.sf.video4j.gwt.client.presenter.PlayerPresenter.PView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
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
    
    @Before
    public void setUp() throws Exception {
    	mEventBus = new CountingEventBus();
    	mPresenter = new Video4JPresenter(
    			mEventBus, 
    			mView,
    			null,
    			new PlayerPresenter(mEventBus, mock(PView.class)), 
    			new ControlPresenter(mEventBus, mock(CView.class)), 
    			mApplicationController,
    			new PlaylistController(mEventBus));
    }

    @Test
    public void whenOnBind_shouldSetExpectedSlotsInView() {
        when(mView.getVideo4JConfig()).thenReturn(newVideo4JConfig());
        
        mPresenter.onBind();
        
        verify(mView, times(2)).setInSlot(anyObject(), any(IsWidget.class));
        verify(mView).setInSlot(anyObject(), isA(PlayerPresenter.class));
        verify(mView).setInSlot(anyObject(), isA(ControlPresenter.class));
    }
    
    @Test
    public void whenOnBind_shouldRetrievePlaylistFromView() {
        when(mView.getVideo4JConfig()).thenReturn(newVideo4JConfig());
        
        mPresenter.onBind();
        
        ArgumentCaptor<ApplicationConfig> oApplicationConfigCaptor = ArgumentCaptor.forClass(ApplicationConfig.class);
        verify(mApplicationController).begin(oApplicationConfigCaptor.capture());
        ApplicationConfig oCapturedApplicationConfig = oApplicationConfigCaptor.getValue();
        assertThat(oCapturedApplicationConfig, is(notNullValue()));
        assertThat(oCapturedApplicationConfig.getPlaylist(), is(notNullValue()));
        assertThat(oCapturedApplicationConfig.getPlaylist().count(), is(1));
    }

    private Video4JConfig newVideo4JConfig() {
        Video4JConfig oVideo4jConfig = new Video4JConfig();
        Playlist oPlaylist = new Playlist();
        HashSet<Track> oTracks = new HashSet<Track>();
        Track oTrack = new Track();
        oTrack.setURL("http://video4j.com/video1.mp4");
        oTracks.add(oTrack);
        oPlaylist.setTracks(oTracks);
        oVideo4jConfig.setPlaylist(oPlaylist);
        return oVideo4jConfig;
    }
    
}