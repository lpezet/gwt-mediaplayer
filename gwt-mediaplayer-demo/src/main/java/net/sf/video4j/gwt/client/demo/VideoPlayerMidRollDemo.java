/**
 * 
 */
package net.sf.video4j.gwt.client.demo;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.video4j.gwt.client.demo.VideoPlayerMidRollDemo;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;


import fr.hd3d.html5.video.client.VideoSource;
import fr.hd3d.html5.video.client.VideoSource.VideoType;
import fr.hd3d.html5.video.client.VideoWidget;
import fr.hd3d.html5.video.client.events.VideoEndedEvent;
import fr.hd3d.html5.video.client.events.VideoTimeUpdateEvent;
import fr.hd3d.html5.video.client.handlers.VideoEndedHandler;
import fr.hd3d.html5.video.client.handlers.VideoTimeUpdateHandler;

/**
 * TODO: figure out how to remove post-roll and re-enable controls
 * 
 * @author luc
 *
 */
public class VideoPlayerMidRollDemo implements EntryPoint {
	
	private Logger mLogger = Logger.getLogger(VideoPlayerMidRollDemo.class.getName());

	private static final String MID_ROLL = "http://static.bouncingminds.com/ads/30secs/country_life_butter.mp4";
	private static final String VIDEO = "http://videos.tripfilms.com/720p/D93A130B1BC3E02EB7AB99812EFB8C00.mp4";
	
	interface Binder extends UiBinder<HTMLPanel, VideoPlayerMidRollDemo> { }
	private static final Binder binder = GWT.create(Binder.class);
	
	/*
	static class OnceOnlyVideoTimeUpdateHandler implements VideoTimeUpdateHandler {
		
		private HandlerRegistration mRegistrationHandler;
		private VideoTimeUpdateHandler mHandler;
		
		public void register(HasVideoHandlers pRegistrar, VideoEndedHandler pHandler) {
			mRegistrationHandler = pRegistrar.addEndedHandler(this);
			mHandler = pHandler;
		}
		
		@Override
		public void onVideoEnded(VideoEndedEvent event) {
			mRegistrationHandler.removeHandler();
			mHandler.onVideoEnded(event);
		}

		@Override
		public void onTimeUpdated(VideoTimeUpdateEvent event) {
			// TODO Auto-generated method stub
			
		}
	}
	*/
	
	@UiField Panel container;
	@UiField Panel videoContainer;
	
	@Override
	public void onModuleLoad() {
		HTMLPanel outer = binder.createAndBindUi(this);
        RootPanel.get("player").add(outer);
        
		final VideoWidget oVideoPlayer = new VideoWidget();
		oVideoPlayer.setControls(true);
		oVideoPlayer.setAutoPlay(true);
        oVideoPlayer.addSource(new VideoSource(VIDEO, VideoType.MP4));
        oVideoPlayer.setPixelSize(500, 400);
        
        oVideoPlayer.addTimeUpdateHandler(new VideoTimeUpdateHandler() {
        	
        	private boolean mMidRollPlayed = false;
			
			@Override
			public void onTimeUpdated(VideoTimeUpdateEvent event) {
				if (mMidRollPlayed) return;
				if (event.getCurrentTime() >= 20) {
					mLogger.log(Level.INFO, "Playing Mid roll...");
					mMidRollPlayed = true;
					oVideoPlayer.playPause(); // pause
					//videoContainer.clear();
					oVideoPlayer.setVisible(false);
					final VideoWidget oMidRollPlayer = new VideoWidget();
					oMidRollPlayer.setPixelSize(500, 400);
					oMidRollPlayer.setControls(false);
					oMidRollPlayer.setAutoPlay(false);
					oMidRollPlayer.setSrc(MID_ROLL);
					oMidRollPlayer.addEndedHandler(new VideoEndedHandler() {
						
						@Override
						public void onVideoEnded(VideoEndedEvent event) {
							mLogger.log(Level.INFO, "Mid roll done!");
							videoContainer.remove(oMidRollPlayer);
							oVideoPlayer.setVisible(true);
							oVideoPlayer.playPause(); // resume
						}
					});
					videoContainer.add(oMidRollPlayer);
					oMidRollPlayer.playPause();
				}
			}
		});
        videoContainer.add(oVideoPlayer);
	}
}
