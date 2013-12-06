package net.sf.video4j.gwt.client.presenter;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.video4j.gwt.client.event.ControlFullScreenEvent;
import net.sf.video4j.gwt.client.event.ControlMuteEvent;
import net.sf.video4j.gwt.client.event.ControlPauseEvent;
import net.sf.video4j.gwt.client.event.ControlPlayEvent;
import net.sf.video4j.gwt.client.event.ControlSeekedEvent;
import net.sf.video4j.gwt.client.event.ControlUnmuteEvent;
import net.sf.video4j.gwt.client.event.ControlVolumeChangeEvent;
import net.sf.video4j.gwt.client.event.PlaylistPlayEvent;
import net.sf.video4j.gwt.client.handler.PlayerUiHandlers;
import net.sf.video4j.gwt.client.model.PlayerParameters;
import net.sf.video4j.gwt.client.player.PlayItem;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.View;

/**
 * @author gumatias
 */
public class PlayerPresenter extends BasePlayerPresenterWidget<PlayerPresenter.PView> {
	
    public interface PView extends View, HasUiHandlers<PlayerUiHandlers> {
        void startPlayer(PlayerParameters pParams);
        void play();
        void pause();
        void mute();
        void unmute();
        void fullScreen();
        void volume(double pValue);
        void seek(double pValue);
        void hide();
        void show();
    }
    
    protected Logger mLogger = Logger.getLogger(this.getClass().getName());

	private PlayItem	mPlaying;
	
    @Inject
    public PlayerPresenter(EventBus pEventBus, PView pView) {
        super(pEventBus, pView);
        getView().setUiHandlers(this);
    }
    
    /*
    private PlayerParameters getPlayerParameters() {
        PlayerParameters oParams = new PlayerParameters()
            .withControls(false)  
            .withAutoPlay(false)
            .withWidthInPixels(500).withHeightInPixels(400)
            .withVideoType(VideoType.MP4)
            .withFileSource("http://videos.tripfilms.com/720p/D93A130B1BC3E02EB7AB99812EFB8C00.mp4");
        return oParams;
    }
    */
    
    @Override
    public void onPlaylistPlayEvent(PlaylistPlayEvent pEvent) {
		mLogger.log(Level.INFO, "Received media in PlaylistPlayEvent.");
		if (pEvent.getPlayItem().getMedia().isAd()) return;

		mLogger.log(Level.INFO, "Received non-ad media in PlaylistPlayEvent. Playing item track #" + pEvent.getPlayItem().getMedia().getId() + "...");

		// TODO: should add all the sources available...so 1 "video" might be multiple Media.
		// TODO: need to pass start and end (e.g. for mid-rolls).
		PlayerParameters oParams = new PlayerParameters()
				.withAutoPlay(pEvent.getPlayItem().isAutoPlay())
				.withControls(false) // TODO: this should come from ApplicationConfig (?)
				.withHeightInPixels(360) // TODO: this should come from the ApplicationConfig
				.withWidthInPixels(640) // TODO: this should come from the ApplicationConfig
				.withMedia(pEvent.getPlayItem().getMedia());
		getView().startPlayer(oParams);
		mPlaying = pEvent.getPlayItem();
		mLogger.log(Level.INFO, "Item now playing (or loading...)");
    }

    @Override
    public void onControlPlayEvent(ControlPlayEvent pEvent) {
        getView().play();
    }
    
    @Override
    public void onControlPauseEvent(ControlPauseEvent pEvent) {
        getView().pause();
    }

    @Override
    public void onControlVolumeChangeEvent(ControlVolumeChangeEvent pEvent) {
        getView().volume(pEvent.getValue());
    }

    @Override
    public void onControlFullScreenEvent(ControlFullScreenEvent pEvent) {
        getView().fullScreen();
    }

    @Override
    public void onControlSeekedEvent(ControlSeekedEvent pEvent) {
        getView().seek(pEvent.getValue());
    }

    @Override
    public void onControlUnmuteEvent(ControlUnmuteEvent pEvent) {
        getView().unmute();
    }

    @Override
    public void onControlMuteEvent(ControlMuteEvent pEvent) {
        getView().mute();
    }
    
	@Override
	public String getPluginId() {
		return this.getClass().getName();
	}

	@Override
	protected PlayItem getPlayingItem() {
		return mPlaying;
	}
    
}