package net.sf.video4j.gwt.client.view;


import net.sf.video4j.gwt.client.handler.PlayerUiHandlers;
import net.sf.video4j.gwt.client.model.PlayerParameters;
import net.sf.video4j.gwt.client.presenter.PlayerPresenter;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import fr.hd3d.html5.video.client.VideoSource;
import fr.hd3d.html5.video.client.VideoWidget;
import fr.hd3d.html5.video.client.events.VideoDurationChangeEvent;
import fr.hd3d.html5.video.client.events.VideoEndedEvent;
import fr.hd3d.html5.video.client.events.VideoErrorEvent;
import fr.hd3d.html5.video.client.events.VideoPauseEvent;
import fr.hd3d.html5.video.client.events.VideoPlayingEvent;
import fr.hd3d.html5.video.client.events.VideoTimeUpdateEvent;
import fr.hd3d.html5.video.client.handlers.VideoDurationChangeHandler;
import fr.hd3d.html5.video.client.handlers.VideoEndedHandler;
import fr.hd3d.html5.video.client.handlers.VideoErrorHandler;
import fr.hd3d.html5.video.client.handlers.VideoPauseHandler;
import fr.hd3d.html5.video.client.handlers.VideoPlayingHandler;
import fr.hd3d.html5.video.client.handlers.VideoTimeUpdateHandler;

/**
 * @author gumatias
 */
public class PlayerView extends ViewWithUiHandlers<PlayerUiHandlers> implements PlayerPresenter.PView {
    
    public interface Binder extends UiBinder<HTMLPanel, PlayerView> {
    }

    @UiField
    VideoWidget mMainPlayer;
	
    @Inject
    public PlayerView(Binder pBinder) {
        initWidget(pBinder.createAndBindUi(this));
        setupHandlers();
    }
    
    private void setupHandlers() {
		mMainPlayer.addTimeUpdateHandler(new VideoTimeUpdateHandler() {			
			@Override
			public void onTimeUpdated(VideoTimeUpdateEvent pEvent) {
				getUiHandlers().onTimeUpdate(pEvent.getCurrentTime());
			}
		});
		mMainPlayer.addErrorHandler(new VideoErrorHandler() {			
			@Override
			public void onError(VideoErrorEvent pEvent) {
				getUiHandlers().onError();
			}
		});
		mMainPlayer.addPlayingHandler(new VideoPlayingHandler() {
			@Override
			public void onPlaying(VideoPlayingEvent pEvent) {
				getUiHandlers().onPlaying();
			}
		});
		mMainPlayer.addPauseHanlder(new VideoPauseHandler() {
			@Override
			public void onPause(VideoPauseEvent pEvent) {
				getUiHandlers().onPause();
			}
		});
		mMainPlayer.addEndedHandler(new VideoEndedHandler() {
			@Override
			public void onVideoEnded(VideoEndedEvent pEvent) {
				getUiHandlers().onEnded();
			}
		});
		mMainPlayer.addDurationChangeHandler(new VideoDurationChangeHandler() {
			@Override
			public void onDurationChange(VideoDurationChangeEvent pEvent) {
				getUiHandlers().onDurationChanged(mMainPlayer.getDuration());
			}
		});
	}

	@Override
    public void startPlayer(PlayerParameters pParams) {
        mMainPlayer.setControls(pParams.hasControls());
        mMainPlayer.setAutoPlay(pParams.isAutoPlay());
        mMainPlayer.setSrc(pParams.getFileSource());
        //mMainPlayer.addSource(new VideoSource(pParams.getFileSource(), pParams.getVideoType()));
        mMainPlayer.setPixelSize(pParams.getWidthInPixels(), pParams.getHeightInPixels());
    }
    
    @Override
    public void play() {
        mMainPlayer.playPause();
    }

    @Override
    public void pause() {
        mMainPlayer.playPause();
    }

    @Override
    public void mute() {
        mMainPlayer.mute();
    }

    @Override
    public void unmute() {
        mMainPlayer.unmute();
    }

    @Override
    public void fullScreen() {
        mMainPlayer.fullScreen();
    }

    @Override
    public void volume(double pValue) {
        mMainPlayer.setVolume(pValue);
    }
    
    @Override
    public void seek(double pValue) {
    	mMainPlayer.setCurrentTime(pValue);
    }
    
}