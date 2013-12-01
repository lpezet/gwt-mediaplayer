package net.sf.video4j.gwt.client.view;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import net.sf.video4j.gwt.client.handler.PlayerUiHandlers;
import net.sf.video4j.gwt.client.model.PlayerParameters;
import net.sf.video4j.gwt.client.model.Source;
import net.sf.video4j.gwt.client.presenter.PlayerPresenter;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import fr.hd3d.html5.video.client.VideoSource;
import fr.hd3d.html5.video.client.VideoSource.VideoType;
import fr.hd3d.html5.video.client.VideoWidget;
import fr.hd3d.html5.video.client.VideoWidget.TypeSupport;
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

	protected Logger			mLogger	= Logger.getLogger(this.getClass().getName());

	public interface Binder extends UiBinder<HTMLPanel, PlayerView> {
	}

	@UiField
	VideoWidget	mPlayerWidget;

	@Inject
	public PlayerView(Binder pBinder) {
		initWidget(pBinder.createAndBindUi(this));
		setupHandlers();

	}

	private void setupHandlers() {
		mPlayerWidget.addTimeUpdateHandler(new VideoTimeUpdateHandler() {
			@Override
			public void onTimeUpdated(VideoTimeUpdateEvent pEvent) {
				getUiHandlers().onTimeUpdate(pEvent.getCurrentTime());
			}
		});
		mPlayerWidget.addErrorHandler(new VideoErrorHandler() {
			@Override
			public void onError(VideoErrorEvent pEvent) {
				getUiHandlers().onError();
			}
		});
		mPlayerWidget.addPlayingHandler(new VideoPlayingHandler() {
			@Override
			public void onPlaying(VideoPlayingEvent pEvent) {
				getUiHandlers().onPlaying();
			}
		});
		mPlayerWidget.addPauseHanlder(new VideoPauseHandler() {
			@Override
			public void onPause(VideoPauseEvent pEvent) {
				getUiHandlers().onPause();
			}
		});
		mPlayerWidget.addEndedHandler(new VideoEndedHandler() {
			@Override
			public void onVideoEnded(VideoEndedEvent pEvent) {
				getUiHandlers().onEnded();
			}
		});
		mPlayerWidget.addDurationChangeHandler(new VideoDurationChangeHandler() {
			@Override
			public void onDurationChange(VideoDurationChangeEvent pEvent) {
				getUiHandlers().onDurationChanged(mPlayerWidget.getDuration());
			}
		});
	}

	@Override
	public void startPlayer(PlayerParameters pParams) {
		mPlayerWidget.setControls(pParams.hasControls());
		mPlayerWidget.setAutoPlay(pParams.isAutoPlay());

		List<VideoType> oSupportedVideoTypes = new ArrayList<VideoType>();
		for (Source s : pParams.getMedia().getSources()) {
			// TODO: Currently ignoring the bitrate, should we take into account?
			if (isTypeOfVideoSupported(oSupportedVideoTypes, s)) {
				oSupportedVideoTypes.add(VideoType.getByType(s.getType()));
				mPlayerWidget.addSource(new VideoSource(s.getURI(), VideoType.getByType(s.getType())));
			}
		}

		// if (pParams.getSources().size() == 1) {
		// mPlayerWidget.setSrc(pParams.getSources().get(0).getSrc());
		// } else {
		// //TODO: detect what's playable
		// }

		// mMainPlayer.addSource(new VideoSource(pParams.getFileSource(), pParams.getVideoType()));
		mPlayerWidget.setPixelSize(pParams.getWidthInPixels(), pParams.getHeightInPixels());
	}

	private boolean isTypeOfVideoSupported(List<VideoType> pSupportedVideoTypes, Source s) {
		return TypeSupport.MAYBE.name().equals(canPlayType(s.getType()))
				&& VideoType.getByType(s.getType()) != null
				&& !pSupportedVideoTypes.contains(VideoType.getByType(s.getType()));
	}

	@Override
	public void play() {
		mPlayerWidget.playPause();
	}

	@Override
	public void pause() {
		mPlayerWidget.playPause();
	}

	@Override
	public void mute() {
		mPlayerWidget.mute();
	}

	@Override
	public void unmute() {
		mPlayerWidget.unmute();
	}

	@Override
	public void fullScreen() {
		mPlayerWidget.fullScreen();
	}

	@Override
	public void volume(double pValue) {
		mPlayerWidget.setVolume(pValue);
	}

	@Override
	public void seek(double pValue) {
		mPlayerWidget.setCurrentTime(pValue);
	}

	@Override
	public void hide() {
		mPlayerWidget.setVisible(false);
	}

	@Override
	public void show() {
		mPlayerWidget.setVisible(true);
	}

	@Override
	public String canPlayType(String pMediaType) {
		return mPlayerWidget.canPlayType(pMediaType).toString();
	}

}