package net.sf.video4j.gwt.client.presenter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import net.sf.video4j.gwt.client.controller.ApplicationController;
import net.sf.video4j.gwt.client.controller.PlaylistController;
import net.sf.video4j.gwt.client.model.ApplicationConfig;
import net.sf.video4j.gwt.client.model.IApplication;
import net.sf.video4j.gwt.client.player.Media;
import net.sf.video4j.gwt.client.player.Playlist;

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
    @Mock
    private ControlPresenter mControlPresenter;
    @Mock
    private PlayerPresenter mPlayerPresenter;
    @Mock
    private AdPresenter mAdPresenter;
    
    @Before
    public void setUp() throws Exception {
    	mEventBus = new CountingEventBus();
    	mPresenter = new Video4JPresenter(
    			mEventBus, 
    			mView,
    			null,
    			mPlayerPresenter, 
    			mControlPresenter,
    			mAdPresenter,
    			mApplicationController,
    			new PlaylistController(mEventBus));
    }

    @Test
    public void whenOnBind_shouldSetExpectedSlotsInView() {
        when(mView.getApplicationConfig()).thenReturn(newApplicationConfig());
        
        mPresenter.onBind();
        
        verify(mView, times(3)).setInSlot(anyObject(), any(IsWidget.class));
        verify(mView).setInSlot(anyObject(), isA(PlayerPresenter.class));
        verify(mView).setInSlot(anyObject(), isA(ControlPresenter.class));
    }
    
    @Test
    public void whenOnBind_shouldRetrievePlaylistFromView() {
        when(mView.getApplicationConfig()).thenReturn(newApplicationConfig());
        
        mPresenter.onBind();
        
        ArgumentCaptor<IApplication> oApplicationCaptor = ArgumentCaptor.forClass(IApplication.class);
        verify(mApplicationController).begin(oApplicationCaptor.capture());
        IApplication oCapturedApplication = oApplicationCaptor.getValue();
        assertThat(oCapturedApplication, is(notNullValue()));
        assertThat(oCapturedApplication.getConfig(), is(notNullValue()));
        assertThat(oCapturedApplication.getPlugins().size(), equalTo(1));
    }

    private ApplicationConfig newApplicationConfig() {
        Playlist oPlaylist = new Playlist();
        Media oTrack = new Media();
        oTrack.setURI("http://video4j.com/video1.mp4");
        oPlaylist.add(oTrack);
        //TODO: fix this
        ApplicationConfig oConfig = new ApplicationConfig();
        return oConfig;
    }
    
}