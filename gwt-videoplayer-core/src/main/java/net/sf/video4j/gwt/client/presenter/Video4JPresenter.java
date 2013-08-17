package net.sf.video4j.gwt.client.presenter;

import java.util.Set;
import java.util.logging.Logger;

import net.sf.video4j.gwt.client.config.model.Video4JConfig;
import net.sf.video4j.gwt.client.controller.ApplicationController;
import net.sf.video4j.gwt.client.controller.PlaylistController;
import net.sf.video4j.gwt.client.model.ApplicationConfig;
import net.sf.video4j.gwt.client.place.NameTokens;
import net.sf.video4j.gwt.client.player.Playlist;
import net.sf.video4j.gwt.client.player.Track;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

/**
 * 
 * @author gumatias
 */
public class Video4JPresenter extends Presenter<Video4JPresenter.V4JView, Video4JPresenter.V4JProxy> {
    
    private Logger mLogger = Logger.getLogger(this.getClass().getName());

    @ProxyStandard
    @NameToken(NameTokens.video4JModule)
    public interface V4JProxy extends ProxyPlace<Video4JPresenter> {
    }

    public interface V4JView extends View {
        Video4JConfig getVideo4JConfig();
    }

    public static final Object SLOT_VIDEO_PLAYER = new Object();
    public static final Object SLOT_CONTROL      = new Object();

    PlayerPresenter            mPlayerPresenter;
    ControlPresenter           mControlPresenter;
    ApplicationController      mApplicationController;
    PlaylistController         mPlaylistController;
    
    @Inject
    Video4JPresenter(
                EventBus pEventBus, 
                V4JView pView, 
                V4JProxy pProxy, 
                PlayerPresenter pPlayerPresenter, 
                ControlPresenter pControlPresenter,
                ApplicationController pApplicationController,
                PlaylistController pPlaylistController
                ) {
        super(pEventBus, pView, pProxy, RevealType.Root);
        mPlayerPresenter = pPlayerPresenter;
        mControlPresenter = pControlPresenter;
        mApplicationController = pApplicationController;
        mPlaylistController = pPlaylistController;
    }
    
    @Override
    protected void onBind() {
        super.onBind();
        setInSlot(SLOT_VIDEO_PLAYER, mPlayerPresenter);
        setInSlot(SLOT_CONTROL, mControlPresenter);
        ApplicationConfig oConfig = retrieveApplicationConfig();
        mApplicationController.begin(oConfig);
    }

    private ApplicationConfig retrieveApplicationConfig() {
        Video4JConfig oVideo4jConfig = getView().getVideo4JConfig();
        mLogger.info("Application JSO configuration=" + oVideo4jConfig.toString());
        Playlist oPlaylist = newPlaylist(oVideo4jConfig.getPlaylist());
        ApplicationConfig oConfig = new ApplicationConfig(oPlaylist);
        return oConfig;
    }

    private Playlist newPlaylist(net.sf.video4j.gwt.client.config.model.Playlist pPlaylist) {
        Playlist oPlaylist = new Playlist();
        Set<net.sf.video4j.gwt.client.config.model.Track> oTracks = pPlaylist.getTracks();
        for (net.sf.video4j.gwt.client.config.model.Track oTrack : oTracks) {
            Track t = new Track();
            t.setURI(oTrack.getURL());
            oPlaylist.add(t);
        }
        return oPlaylist;
    }

}