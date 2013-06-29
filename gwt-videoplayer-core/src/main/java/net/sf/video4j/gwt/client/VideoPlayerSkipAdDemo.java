/**
 * 
 */
package net.sf.video4j.gwt.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;

import fr.hd3d.html5.video.client.VideoSource;
import fr.hd3d.html5.video.client.VideoSource.VideoType;
import fr.hd3d.html5.video.client.VideoWidget;
import fr.hd3d.html5.video.client.events.VideoEndedEvent;
import fr.hd3d.html5.video.client.handlers.HasVideoHandlers;
import fr.hd3d.html5.video.client.handlers.VideoEndedHandler;

/**
 * @author luc
 * 
 */
public class VideoPlayerSkipAdDemo implements EntryPoint {

	private Logger mLogger = Logger.getLogger(VideoPlayerSkipAdDemo.class
			.getName());

	interface Binder extends UiBinder<HTMLPanel, VideoPlayerSkipAdDemo> {
	}

	private static final Binder binder = GWT.create(Binder.class);

	private static final String PRE_ROLL = "http://static.bouncingminds.com/ads/30secs/country_life_butter.mp4";
	private static final String VIDEO = "http://videos.tripfilms.com/720p/D93A130B1BC3E02EB7AB99812EFB8C00.mp4";

	@UiField
	Panel container;
	@UiField
	Panel videoContainer;
	@UiField
	Panel overlayContainer;

	static class OnceOnlyVideoEndedHandler implements VideoEndedHandler {

		private HandlerRegistration mRegistrationHandler;
		private VideoEndedHandler mHandler;

		public void register(HasVideoHandlers pRegistrar,
				VideoEndedHandler pHandler) {
			mRegistrationHandler = pRegistrar.addEndedHandler(this);
			mHandler = pHandler;
		}

		@Override
		public void onVideoEnded(VideoEndedEvent event) {
			mRegistrationHandler.removeHandler();
			mHandler.onVideoEnded(event);
		}
	}

	@Override
	public void onModuleLoad() {
		HTMLPanel outer = binder.createAndBindUi(this);
		RootPanel.get("player").add(outer);

		final VideoWidget videoPlayer = new VideoWidget();
		videoPlayer.setControls(false);
		videoPlayer.setAutoPlay(true);
		videoPlayer.addSource(new VideoSource(PRE_ROLL, VideoType.MP4));
		videoPlayer.setPixelSize(500, 400);

		countDownToSkipAd(videoPlayer);
		
		OnceOnlyVideoEndedHandler oHandler = new OnceOnlyVideoEndedHandler();
		oHandler.register(videoPlayer, new VideoEndedHandler() {
			@Override
			public void onVideoEnded(VideoEndedEvent event) {
				mLogger.log(Level.INFO, "Pre-roll done. Now playing videos...");
				overlayContainer.clear();
				playVideo(videoPlayer);
			}

		});
		videoContainer.add(videoPlayer);
	}

	private void playVideo(final VideoWidget videoPlayer) {
		videoPlayer.setSrc(VIDEO);
		videoPlayer.setControls(true);
		videoPlayer.playPause();
	}

	private void countDownToSkipAd(final VideoWidget videoPlayer) {
		final Panel oPanel = new HorizontalPanel();
		final Label oLabel = new Label();
		oPanel.add(oLabel);
		overlayContainer.add(oPanel);
		Timer t = new Timer() {
			int oSeconds = 5;
			@Override
			public void run() { 
				if (oSeconds == 0) {
					cancel();
					oLabel.setText(null);
					addSkipAdButton(oPanel, videoPlayer);
				}
				else oLabel.setText("You can skip to video in " + (oSeconds--));
			}
		};
		t.scheduleRepeating(1000);
	}
	
	private void addSkipAdButton(Panel pPanel, final VideoWidget pVideoPlayer) {
		Anchor oSkipAd = new Anchor("Skip Ad");
		oSkipAd.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				pVideoPlayer.playPause();
				overlayContainer.clear();
				playVideo(pVideoPlayer);
			}
		});
		pPanel.add(oSkipAd);		
	}

}
